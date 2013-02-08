@ECHO OFF
echo - - - - - - - - - - - - - - - - - - -
echo **** COMPILING HELPER CLASSES *******
echo - - - - - - - - - - - - - - - - - - -
javac -d "classes" src/se/markusmaga/lth/pt/helper/*.java
echo - - - - - - - - - - - - - - - - - - -
echo **** COMPILING REGISTER CLASSES *****
echo - - - - - - - - - - - - - - - - - - -
javac -d "classes" src/se/markusmaga/lth/pt/register/*.java
echo - - - - - - - - - - - - - - - - - - -
echo **** COMPILING TEST CLASSES *****
echo - - - - - - - - - - - - - - - - - - -
javac -d "classes" src/*.java
echo - - - - - - - - - - - - - - - - - - -
pause