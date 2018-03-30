

mvn clean package; docker build -t 172.16.0.32:60080/tsf/tsf-product .; docker push 172.16.0.32:60080/tsf/tsf-product
mvn clean package; docker build -t 172.16.0.32:60080/tsf/tsf-order .; docker push 172.16.0.32:60080/tsf/tsf-order
mvn clean package; docker build -t 172.16.0.32:60080/tsf/tsf-inventory .; docker push 172.16.0.32:60080/tsf/tsf-inventory
mvn clean package; docker build -t 172.16.0.32:60080/tsf/tsf-front .; docker push 172.16.0.32:60080/tsf/tsf-front

