.PHONY: build clean run
build:
	mkdir classes;
	javac src/*.java -d classes;
	javac -cp classes src/*.java -d classes;
clean:
	rm -r classes
	rm test0.out test1.out test2.out
run:
	java -Xmx512m -cp classes Main test0.in test0.out
	java -Xmx512m -cp classes Main test1.in test1.out
	java -Xmx512m -cp classes Main test2.in test2.out
