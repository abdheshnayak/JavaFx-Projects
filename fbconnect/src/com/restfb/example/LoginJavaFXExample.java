/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2018 Mark Allen, Norbert Bartels.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package com.restfb.example;
import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient.AccessToken;
import com.restfb.scope.ScopeBuilder;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

import static com.restfb.logging.RestFBLogger.CLIENT_LOGGER;
public class LoginJavaFXExample extends Application {

  private static final String SUCCESS_URL = "https://www.facebook.com/connect/login_success.html";

  private String appId;
  private String appSecret;

  public void start(Stage stage) {
    // parse arguments
    appId = getParameters().getRaw().get(0);
    appSecret = getParameters().getRaw().get(1);

    WebView webView = new WebView();
    WebEngine webEngine = webView.getEngine();

    // create the scene
    stage.setTitle("Facebook Login Example");
    // use quite a wide window to handle cookies popup nicely
    stage.setScene(new Scene(new VBox(webView), 1000, 600, Color.web("#666970")));
    stage.show();

    // obtain Facebook access token by loading login page
    DefaultFacebookClient facebookClient = new DefaultFacebookClient(com.restfb.Version.LATEST);
    String loginDialogUrl = facebookClient.getLoginDialogUrl(appId, SUCCESS_URL, new ScopeBuilder());
    webEngine.load(loginDialogUrl + "&display=popup&response_type=code");
    webEngine.locationProperty().addListener((property, oldValue, newValue) -> {
          if (newValue.startsWith(SUCCESS_URL)) {
            System.out.println("LoggedIn");

            // extract access token
            CLIENT_LOGGER.debug(newValue);
            int codeOffset = newValue.indexOf("code=");
            String code = newValue.substring(codeOffset + "code=".length());
            AccessToken accessToken = facebookClient.obtainUserAccessToken(
                appId, appSecret, SUCCESS_URL, code);

            GraphReaderExample g=new GraphReaderExample(accessToken.getAccessToken());


            g.selection();
            // trigger further code's execution
            consumeAccessToken(accessToken);

            // close the app as goal was reached
//            stage.close();

          } else if ("https://www.facebook.com/dialog/close".equals(newValue)) {
            throw new IllegalStateException("dialog closed");
          }
        }
    );
  }

  private static void consumeAccessToken(AccessToken accessToken) {
    CLIENT_LOGGER.debug("Access token: " + accessToken.getAccessToken());
    CLIENT_LOGGER.debug("Expires: " + accessToken.getExpires());
  }

  public static void main(String[] args) {
    String[] arg=new String[3];
    arg[0]="501763990407283"; //App ID
    arg[1]="0f5562ac4f99e13ef4845c1b08c13a3c"; //App Secrate Key
    launch(arg);
  }
}
