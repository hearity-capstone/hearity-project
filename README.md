
# ChatBot Hearity


## Description

This project contains the ChatBot feature, which is one of the functionalities of the Hearity application.

---

In building this chatbot, the first step is to prepare a dataset in JSON format, containing the main intents, each with a tag, user input patterns, and chatbot responses. The dataset is cleaned using a clean_text function to remove irrelevant characters and converted into a DataFrame for easier manipulation. Next, tokenization is done using Keras's Tokenizer, which converts the text into numerical sequences. These sequences are padded to ensure uniform input length. The intent labels are encoded into numbers for training the model.The model itself is a neural network with key layers: an embedding layer to convert input sequences into vector representations, a bidirectional LSTM layer to better understand context, a dense layer for further processing, and a dropout layer to prevent overfitting. The model is compiled with the Adam optimizer and categorical cross-entropy loss function. It is then trained using the prepared input data.





