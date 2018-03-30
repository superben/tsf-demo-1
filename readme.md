
<pre>

mvn install:install-file \
-Dfile=./spring-cloud-tsf-sleuth-0.0.1-SNAPSHOT.jar \
-DpomFile=spring-cloud-tsf-1.0.1.BUILD-SNAPSHOT.pom \
-DgroupId=com.tencent.tsf \
-DartifactId=spring-cloud-tsf-sleuth \
-Dversion=0.0.1-SNAPSHOT \
-Dpackaging=jar


mvn install:install-file \
-Dfile=tcc-transaction-core-0.0.2-SNAPSHOT.jar \
-DpomFile=tcc-transaction-core-0.0.2-SNAPSHOT.pom \
-DgroupId=com.tencent.tsf.transaction \
-DartifactId=tcc-transaction-core \
-Dversion=0.0.2-SNAPSHOT \
-Dpackaging=jar


mvn install:install-file \
-Dfile=tcc-transaction-springcloud-0.0.2-SNAPSHOT.jar \
-DpomFile=tcc-transaction-springcloud-0.0.2-SNAPSHOT.pom \
-DgroupId=com.tencent.tsf.transaction \
-DartifactId=tcc-transaction-springcloud \
-Dversion=0.0.2-SNAPSHOT \
-Dpackaging=jar


mvn clean package; docker build -t 172.16.0.32:60080/tsf/tsf-product .; docker push 172.16.0.32:60080/tsf/tsf-product
mvn clean package; docker build -t 172.16.0.32:60080/tsf/tsf-order .; docker push 172.16.0.32:60080/tsf/tsf-order
mvn clean package; docker build -t 172.16.0.32:60080/tsf/tsf-inventory .; docker push 172.16.0.32:60080/tsf/tsf-inventory
mvn clean package; docker build -t 172.16.0.32:60080/tsf/tsf-front .; docker push 172.16.0.32:60080/tsf/tsf-front
</pre>

`
mvn install:install-file \
-Dfile=./spring-cloud-tsf-sleuth-0.0.1-SNAPSHOT.jar \
-DpomFile=spring-cloud-tsf-1.0.1.BUILD-SNAPSHOT.pom \
-DgroupId=com.tencent.tsf \
-DartifactId=spring-cloud-tsf-sleuth \
-Dversion=0.0.1-SNAPSHOT \
-Dpackaging=jar

`

`
kubectl delete pod --all
`

`kubectl get pods --all-namespaces`

`kubectl get pods`

`kubectl create -f demo.yaml`

<pre>
kubectl logs tsf-product -c product
kubectl logs tsf-order -c order 
kubectl logs tsf-inventory -c inventory
kubectl logs tsf-front -c front

kubectl exec -it tsf-front -c front sh

kubectl port-forward tsf-front  9080:8080

ssh -L 55555:172.16.0.25:9080 root@111.230.112.90

</pre>






