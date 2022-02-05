# BedrockSignFixer
Plugin to work around the bug that prevents editing signs on GeyserMC Switch and Android devices.

## important
It does not fundamentally solve the problem of this plugin!
The workaround is to send the integrated player another form to edit the signs!

# Uses
Place sign, enter the appropriate text, and send it.
Then a form will be sent to you, and you can enter text in the text boxes of each line and submit it to edit the sign.

If you are using Bungee/Velocity, you need to install Floodgate on your backend server and enable "send-floodgate-data" on proxy Floodgate.

# Config
| KEY                | VALUE                                              |
|--------------------|----------------------------------------------------|
| target_NSW_ANDROID | Submit the form to Switch and Android players only |
| form_title         | Form title                                         |
| form_label         | Form Description                                   |
| form_line          | Text in text boxes 1 through 4                     |