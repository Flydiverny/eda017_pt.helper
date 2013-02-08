@ECHO OFF
echo - - - - - - - - - - - - - - - - - - -
echo **** COMPILING HELPER CLASSES *******
echo - - - - - - - - - - - - - - - - - - -
javac -d "classes" src/se/markusmaga/lth/pt/helper/*.java
echo - - - - - - - - - - - - - - - - - - -
echo **** COMPILING tiles CLASSES *****
echo - - - - - - - - - - - - - - - - - - -
javac -d "classes" src/se/markusmaga/lth/pt/maze/tiles/*.java
echo - - - - - - - - - - - - - - - - - - -
echo **** COMPILING maze CLASSES *****
echo - - - - - - - - - - - - - - - - - - -
javac -d "classes" src/se/markusmaga/lth/pt/maze/*.java
echo - - - - - - - - - - - - - - - - - - -
echo **** COMPILING TEST CLASSES *****
echo - - - - - - - - - - - - - - - - - - -
javac -d "classes" src/*.java
echo - - - - - - - - - - - - - - - - - - -
pause
