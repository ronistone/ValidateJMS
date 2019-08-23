
if [ -n "$1" ]
 then
	node_id=$1;

	docker run -d --rm \
	      -v `pwd`:/c \
	      -v ~/.m2:/home/messageUser/.m2 \
	      -w /c \
	      --name validate-jms-${node_id} \
	      validatejms
else
	echo 'informe o id do node';
fi
