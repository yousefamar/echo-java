all:
	javac -d bin src/*.java

clean :
	find . -name "*.class" -type f -delete
