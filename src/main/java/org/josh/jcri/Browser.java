package org.josh.jcri;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;

/**Execute browser instance that will be controlled by JCRI.
 <p>This class supports user execute new browser instance with specified extra command line options.
 </p>
 <p>About browser path:</p>
 <p>We search chrome's executable file location in the following order:
 <ol><li>Constructor parameter {@literal browserPath}.</li>
 <li>If system property {@literal org.josh.jcri.BrowserPath} is specified, we use it.</li>
 <li>In windows, we use path stores in registry value
 {@literal "HKLM\SOFTWARE\Microsoft\Windows\CurrentVersion\App Paths\chrome.exe"}. In other system,
 we will directly call {@literal "chrome"} and {@literal "chrome-stable"}.</li></ol></p>

 <p>About command line options:</p>
 <p>In constructor methods, user can specify a list of command line options (with or without leading
 double dash). The most important option is setting remote debugging port (for example,
 {@literal --remote-debugging-port} in chrome) so that JCRI can communicate or control this browser
 instance through chrome's debugging protocol. There are also some required options must be enabled
 together with debug port. See browser's manual.</p>
 <p>For complete options supported by chrome, see {@url https://peter.sh/experiments/chromium-command-line-switches/}.</p>

 <p>Note chrome in windows:</p>
 <p>If {@literal --user-data-dir} does not specified, the remote debugging port will not bind and
 created chrome process will be automatically as sub-process of another already opened chrome
 instance, and {@link #close()} will not work.</p>
 <p>Thread safe: <strong>No.</strong></p>
 @author Joshua */
@ParametersAreNonnullByDefault
public class Browser {
    /**Browser path property name.*/
    private static final String BrowserPathPropertyName = "org.josh.jcri.BrowserPath";
    /**Guessed default chrome path in MacOS.*/
    private static final String DefaultMacChromePath = "/Applications/Google Chrome.app/Contents/MacOS/Google Chrome";
    /**Registry key path of chrome in Windows.*/
    private static final String WindowsChromeRegistryKeyPath = "HKLM\\SOFTWARE\\Microsoft\\Windows\\CurrentVersion\\App Paths\\chrome.exe";
    /**Chrome path returned by Windows's reg.*/
    private static final Pattern WindowsRegPattern = Pattern.compile("\\W+\\(Default\\)\\W+REG_SZ\\W+(.*)");

    private Process _browser;
    private String _browserPath = null;
    private Map<String, Object> _cmdOptions;

    /**Create builder.*/
    public static BrowserBuilder newBuilder() { return new BrowserBuilder(); }

    /**Create new browser instance by given browser path and a map of options.*/
    public Browser(String browserPath, Map<String, Object> options) {
        _browserPath = browserPath;
        _cmdOptions = options;
    }
    /**Create new browser instance by using default path and a given map of options.*/
    public Browser(Map<String, Object> options) { _cmdOptions = options; }

    /**Start process.
     <p>If this method was called before, previous running browser process will be close first.</p>
     @throws IOException if cannot create browser instance (possibly command not found). */
    public void start() throws IOException {
        if (_browser != null && _browser.isAlive())     close();
        //! Detect if current OS is windows
        final boolean isWindows = System.getProperty("os.name").toLowerCase().startsWith("windows");
        final boolean isMac = System.getProperty("os.name").toLowerCase().startsWith("mac");

        //! Handles command line options
        String[] cmd = new String[2 + _cmdOptions.size()];
        cmd[0] = "";
        cmd[1] = "--jcri";
        int i = 2;
        for (Map.Entry<String, Object> e: _cmdOptions.entrySet()) {
            final String prefix = e.getKey().startsWith("--") ? "" : "--";
            final String value = e.getValue() != null ? ("=" + e.getValue().toString()) : "";
            cmd[i++] = String.format("%s%s%s", prefix, e.getKey(), value);
        }

        //! Create browser process
        //! User specified browser path, if fail, we should notify them immediately
        if (_browserPath != null) {
            cmd[0] = _browserPath;
            _browser = Runtime.getRuntime().exec(cmd);
            return;
        }

        //! Try using values in system property
        //! User specified browser path in System property, if fail, we should notify them immediately
        cmd[0] = System.getProperty(BrowserPathPropertyName);
        if (cmd[0] != null) {
            _browser = Runtime.getRuntime().exec(cmd);
            return;
        }

        //! Try Windows registry path
        if (isWindows) {
            Process reg = Runtime.getRuntime().exec("reg query \"" + WindowsChromeRegistryKeyPath + "\" /ve");
            try {
                BufferedReader in = new BufferedReader(new BufferedReader(new InputStreamReader(reg.getInputStream())));
                final StringBuilder sb = new StringBuilder();
                String line;
                while ((line = in.readLine()) != null)  sb.append(line);
                in.close();

                Matcher m = WindowsRegPattern.matcher(sb.toString());
                if (m.find()) {
                    cmd[0] = m.group(1);
                    _browser = Runtime.getRuntime().exec(cmd);
                    return;
                }
            }
            catch (IOException e) {
                throw new IllegalStateException("Fail to query reg key of chrome path in Windows.", e);
            }
        }

        //! Try Mac's application path
        if (isMac) {
            cmd[0] = DefaultMacChromePath;
            try { _browser = Runtime.getRuntime().exec(cmd); return; }
            catch (IOException e) { /* Skip and try next possible name. */ }
        }

        //! Try "google-chrome"
        cmd[0] = "google-chrome";
        try { _browser = Runtime.getRuntime().exec(cmd); return; }
        catch (IOException e) { /* Skip and try next possible name. */ }
        //! Try "chrome"
        cmd[0] = "chrome";
        try { _browser = Runtime.getRuntime().exec(cmd); return; }
        catch (IOException e) { /* Skip and try next possible name. */ }
        //! Try "google-chrome-stable"
        cmd[0] = "google-chrome-stable";
        try { _browser = Runtime.getRuntime().exec(cmd); return; }
        catch (IOException e) { /* Skip and try next possible name. */ }
        //! Try "chrome-stable"
        cmd[0] = "chrome-stable";
        try { _browser = Runtime.getRuntime().exec(cmd); return; }
        catch (IOException e) { /* Skip and try next possible name. */ }
        //! Try "chromium"
        cmd[0] = "chromium";
        _browser = Runtime.getRuntime().exec(cmd);
    }

    /**Close running browser instance.*/
    public void close() {
        if (_browser != null && _browser.isAlive()) {
            _browser.destroyForcibly();
            try { _browser.waitFor(); }
            catch (InterruptedException e) { Thread.currentThread().interrupt(); }
            _browser = null;
        }
    }


    /**Builder class of Browser.
     <p>Thread safe: <strong>no.</strong></p>*/
    @ParametersAreNonnullByDefault
    public static class BrowserBuilder {
        private Map<String, Object> _cmdOptions = new HashMap<>();
        private String _browserPath;

        private BrowserBuilder() {}

        /**Set browser's executable path.
         @param path path to browser executable. Use null or "" to specify using default value. */
        public BrowserBuilder setBrowserPath(@Nullable String path) {
            _browserPath = path != null && path.equals("") ? null : path;   return this;
        }
        /**Set command line option.
         If desired command line option does not have value, set the value parameter to null. */
        public BrowserBuilder setOption(String key, @Nullable Object value) {
            _cmdOptions.put(key, value);    return this;
        }
        /**Set command line option without value.*/
        public BrowserBuilder setOption(String key) { return setOption(key, null); }
        /**Remove previous set command line option.*/
        public BrowserBuilder clearOption(String key) { _cmdOptions.remove(key);    return this; }

        /**Build new Browser instance.*/
        public Browser build() { return new Browser(_browserPath, _cmdOptions); }
    } // ! class BrowserBuilder
} // ! class Browser
