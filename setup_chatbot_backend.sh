#!/bin/bash

echo "Installing Ollama..."
curl -fsSL https://ollama.com/install.sh | sh

echo "Would you like to access Ollama locally or from anywhere?"
echo "1 for local"
echo "2 for anywhere"
read -p "Enter your choice (1 or 2): " choice

if [ "$choice" -eq 2 ]; then
    echo "Starting Ollama server for access from anywhere..."
    OLLAMA_HOST=0.0.0.0 OLLAMA_ORIGINS="*" ollama serve &
else
    echo "Starting Ollama server for local access..."
    ollama serve &
fi

sleep 5

echo "Running deepseek-r1:1.5b model..."
ollama run deepseek-r1:1.5b