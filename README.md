# AI Micro Degree Application Documentation

## Table of Contents

1. [Overview](#overview)
2. [Tech Stack](#tech-stack)
3. [Project Structure](#project-structure)
4. [Main Activity Logic](#main-activity-logic)
   1. [WebView Setup](#webview-setup)
   2. [JavaScript Injection](#javascript-injection)
   3. [Chat Popup Implementation](#chat-popup-implementation)
   4. [Dynamic Menu Injection](#dynamic-menu-injection)
5. [External API Integration](#external-api-integration)

## Overview

The AI Micro Degree application is designed to provide users with an interactive learning experience via a dynamic and responsive WebView. The application leverages advanced web technologies and mobile UI components to create a seamless interface between users and online content. 

This document provides an in-depth explanation of how the application works, including technical details regarding the WebView setup, JavaScript injection, and integration with external APIs.

## Tech Stack

- **Android Studio**: The primary development environment for building Android applications.
- **WebView**: Used to display and interact with web content within the app.
- **JavaScript**: Utilized for injecting dynamic content (e.g., chatbot, menu).
- **Ollama**: A lightweight backend for running AI models locally or remotely.
- **deepseek-r1:1.5b**: The AI model used for natural language processing.

## Project Structure

The project is structured with a single activity (`MainActivity`) in the Android application. The main logic is encapsulated within this activity, where a `WebView` component is initialized and customized to load the `AI Micro Degree` website and inject additional dynamic features into the web content.

## Main Activity Logic

### WebView Setup

The core of the application's user interface relies on the use of `WebView`, a powerful Android component that allows embedding and interacting with web content directly within a native app. The following operations are performed:

1. **Disabling the Action Bar**:
   - The action bar is hidden using `getSupportActionBar().hide()` to ensure a clean and immersive experience for the user.

2. **Configuring WebView Settings**:
   - JavaScript is enabled through `webSettings.setJavaScriptEnabled(true)` to ensure the dynamic functionality of the webpage is fully operational.
   - Local DOM storage and file access are enabled to support a smooth and robust user interaction experience with the website.

### JavaScript Injection

The WebView is further enhanced with dynamic content injection via JavaScript. After the page is loaded (`onPageFinished`), JavaScript is injected to modify the web content in real-time. This is achieved using the `evaluateJavascript` method, which executes JavaScript code within the WebView's context.

### Chat Popup Implementation

A custom chatbot feature is implemented using JavaScript. Upon page load, the following elements are dynamically created:

- **Chat Button**: A floating button with the 💬 icon is placed at the bottom-right corner of the screen. When clicked, it toggles the visibility of the chat container.
  
- **Chat Container**: A hidden chat window that appears on demand, containing a header, close button, messages area, and input box.
  
- **Message Handling**: The user can type messages into the input field. Upon pressing Enter, the message is sent to the **Ollama** backend, which processes the input and returns a relevant AI-generated response. The response is then displayed in the chat window.

### Dynamic Menu Injection

Another feature of the application involves dynamically injecting a menu item into the website's side menu. This is done by selecting the DOM element for the side menu and appending a new list item (`li`) that links to an "Answers" page. The injected item includes:

- **Icon and Label**: A visual icon and label are added to the menu, representing the "Answers" section.
- **Tooltip**: A tooltip is applied to the icon for enhanced user experience and clarity.

This approach allows the web content to be customized at runtime, providing a tailored experience that fits the needs of the application.

## External API Integration

The chatbot functionality integrates with the **Ollama** backend, enabling natural language processing and AI-driven conversations. The integration is achieved via a `fetch` request to the Ollama API, passing the user's input and retrieving a response. The Ollama server can be configured to run locally or allow access from anywhere, depending on the user's preference.

### Example Integration Code

```javascript
let response = await fetch('http://localhost:11434/api/generate', {
   method: 'POST',
   headers: {
       'Content-Type': 'application/json'
   },
   body: JSON.stringify({
       model: 'deepseek-r1:1.5b',
       prompt: userMessage,
       stream: false
   })
});
```

### Key Features:
1. **Local or Remote Access**: The Ollama server can be configured to run locally (`OLLAMA_HOST=127.0.0.1`) or allow access from anywhere (`OLLAMA_HOST=0.0.0.0`).
2. **Model Support**: The `deepseek-r1:1.5b` model is used for generating responses.
3. **Streaming Option**: The `stream` parameter can be set to `true` or `false` depending on whether real-time streaming of responses is required.
