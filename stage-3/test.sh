javac --release 8  *.java

java Server &

sleep 1

java Client &
java Client &
java Client &
java Client &

wait 
echo "All clients have finished"