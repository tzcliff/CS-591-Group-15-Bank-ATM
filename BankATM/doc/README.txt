In order to execute make the following steps:

1) Download the respective JavaFX sdk from here https://gluonhq.com/products/javafx/
2) Follow step-by-step the instructions to set up JavaFX as stated here https://openjfx.io/openjfx-docs/#install-javafx
3) In the second step replace the command suggested with this javac --module-path $PATH_TO_FX --add-modules javafx.graphics,javafx.controls bank/Main.java
4) In the third step replace the command suggested with this java --module-path $PATH_TO_FX --add-modules javafx.graphics,javafx.controls bank.Main
5) That should be everything you need, if not, feel free to contact me at aflpd@bu.edu for any help regarding JavaFX setup