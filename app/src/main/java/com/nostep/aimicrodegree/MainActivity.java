package com.nostep.aimicrodegree;

import android.os.Bundle;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    WebView myWeb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        setContentView(R.layout.activity_main);

        myWeb = findViewById(R.id.myWeb);
        WebSettings webSettings = myWeb.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setDomStorageEnabled(true);
        webSettings.setAllowFileAccess(true);
        webSettings.setAllowContentAccess(true);

        myWeb.setWebViewClient(new WebViewClient());
        myWeb.setWebChromeClient(new WebChromeClient());

        myWeb.loadUrl("https://aimicrodegree.org/login");

        myWeb.postDelayed(this::injectChatPopup, 3000);
    }

    private void injectChatPopup() {
        String jsCode = "var chatButton = document.createElement('div');" +
                "chatButton.innerHTML = '💬';" +
                "chatButton.style.position = 'fixed';" +
                "chatButton.style.bottom = '20px';" +
                "chatButton.style.right = '20px';" +
                "chatButton.style.width = '50px';" +
                "chatButton.style.height = '50px';" +
                "chatButton.style.background = '#0078ff';" +
                "chatButton.style.color = 'white';" +
                "chatButton.style.fontSize = '24px';" +
                "chatButton.style.textAlign = 'center';" +
                "chatButton.style.lineHeight = '50px';" +
                "chatButton.style.borderRadius = '50%';" +
                "chatButton.style.boxShadow = '0px 0px 10px rgba(0,0,0,0.2)';" +
                "chatButton.style.cursor = 'pointer';" +
                "chatButton.style.zIndex = '1000';" +
                "document.body.appendChild(chatButton);" +

                "var chatContainer = document.createElement('div');" +
                "chatContainer.style.position = 'fixed';" +
                "chatContainer.style.bottom = '80px';" +
                "chatContainer.style.right = '20px';" +
                "chatContainer.style.width = '300px';" +
                "chatContainer.style.height = '400px';" +
                "chatContainer.style.background = 'white';" +
                "chatContainer.style.borderRadius = '10px';" +
                "chatContainer.style.boxShadow = '0px 0px 10px rgba(0,0,0,0.2)';" +
                "chatContainer.style.padding = '10px';" +
                "chatContainer.style.display = 'none';" +
                "chatContainer.style.flexDirection = 'column';" +
                "chatContainer.style.zIndex = '1000';" +
                "document.body.appendChild(chatContainer);" +

                "var chatHeader = document.createElement('div');" +
                "chatHeader.innerText = 'Chatbot';" +
                "chatHeader.style.background = '#0078ff';" +
                "chatHeader.style.color = 'white';" +
                "chatHeader.style.padding = '10px';" +
                "chatHeader.style.textAlign = 'center';" +
                "chatContainer.appendChild(chatHeader);" +

                "var closeButton = document.createElement('span');" +
                "closeButton.innerHTML = '✖';" +
                "closeButton.style.position = 'absolute';" +
                "closeButton.style.top = '5px';" +
                "closeButton.style.right = '10px';" +
                "closeButton.style.cursor = 'pointer';" +
                "chatHeader.appendChild(closeButton);" +

                "var chatMessages = document.createElement('div');" +
                "chatMessages.style.flex = '1';" +
                "chatMessages.style.overflowY = 'auto';" +
                "chatContainer.appendChild(chatMessages);" +

                "var chatInput = document.createElement('input');" +
                "chatInput.type = 'text';" +
                "chatInput.placeholder = 'Ask me anything...';" +
                "chatInput.style.width = '100%';" +
                "chatInput.style.padding = '5px';" +
                "chatInput.style.borderTop = '1px solid #ddd';" +
                "chatContainer.appendChild(chatInput);" +

                "chatInput.addEventListener('keypress', async function(event) {" +
                "   if (event.key === 'Enter') {" +
                "       let userMessage = this.value;" +
                "       this.value = '';" +
                "       chatMessages.innerHTML += '<div>User: ' + userMessage + '</div>';" +
                "       let response = await fetch('https://api.openai.com/v1/chat/completions', {" +
                "           method: 'POST'," +
                "           headers: {" +
                "               'Authorization': 'Bearer MY_KEY'," +
                "               'Content-Type': 'application/json'" +
                "           }," +
                "           body: JSON.stringify({" +
                "               model: 'gpt-3.5-turbo'," +
                "               messages: [{ role: 'user', content: userMessage }]" +
                "           })" +
                "       });" +
                "       let data = await response.json();" +
                "       chatMessages.innerHTML += '<div>Bot: ' + data.choices[0].message.content + '</div>';" +
                "   }" +
                "});" +

                "chatButton.addEventListener('click', function() {" +
                "   chatContainer.style.display = chatContainer.style.display === 'none' ? 'flex' : 'none';" +
                "});" +

                "closeButton.addEventListener('click', function() {" +
                "   chatContainer.style.display = 'none';" +
                "});";

        myWeb.evaluateJavascript(jsCode, null);
    }
}
