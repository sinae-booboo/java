build:
	javac src/ioopm/inl4/*.java

run:
	java -cp ./src ioopm.inl4.Main

clean:
	rm src/ioopm/inl4/*.class

