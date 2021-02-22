##### 2021-02-22

### Command

aws ecr get-login-password --region ap-northeast-2 | docker login --username AWS --password-stdin 496278789073.dkr.ecr.ap-northeast-2.amazonaws.com

docker login --username AWS -p $(aws ecr get-login-password --region ap-northeast-2) 496278789073.dkr.ecr.ap-northeast-2.amazonaws.com/

---

##### 프로젝트 루트 디렉토리 이동 및 프로젝트 패키징(.jar file) 후

1. Create ECR repository

```
aws ecr create-repository --repository-name skteam01-(프로젝트명) --region ap-northeast-2
```

2. Docker build

```
Docker build -t skteam01-(프로젝트명) .
```

3. Tag docker image

```
docker tag skteam01-(프로젝트명):latest 496278789073.dkr.ecr.ap-northeast-2.amazonaws.com/skteam01-(프로젝트명):latest
```

4. Docker push

```
docker push 496278789073.dkr.ecr.ap-northeast-2.amazonaws.com/skteam01-(프로젝트명):latest
```

모든 프로젝트 반복(order, delivery, product, gateway)

---

##### 쿠버네티스 설정

1. kubectl create deploy skteam01-(프로젝트명) --image=496278789073.dkr.ecr.ap-northeast-2.amazonaws.com/skteam01-(프로젝트명):latest
2. kubectl expose deploy skteam01-(프로젝트명) --type=ClusterIP --port=8080
3. kubectl expose deploy skteam01-gateway --type=LoadBalancer --port=8080




---

docker tag skteam01-order:latest 496278789073.dkr.ecr.ap-northeast-2.amazonaws.com/skteam01-order:latest
docker tag skteam01-delivery:latest 496278789073.dkr.ecr.ap-northeast-2.amazonaws.com/skteam01-delivery:latest
docker tag skteam01-product:latest 496278789073.dkr.ecr.ap-northeast-2.amazonaws.com/skteam01-product:latest
docker tag skteam01-gateway:latest 496278789073.dkr.ecr.ap-northeast-2.amazonaws.com/skteam01-gateway:latest

docker push 496278789073.dkr.ecr.ap-northeast-2.amazonaws.com/skteam01-order
docker push 496278789073.dkr.ecr.ap-northeast-2.amazonaws.com/skteam01-delivery
docker push 496278789073.dkr.ecr.ap-northeast-2.amazonaws.com/skteam01-product
docker push 496278789073.dkr.ecr.ap-northeast-2.amazonaws.com/skteam01-gateway

kubectl create deploy skteam01-order --image=496278789073.dkr.ecr.ap-northeast-2.amazonaws.com/skteam01-order:latest
kubectl create deploy skteam01-delivery --image=496278789073.dkr.ecr.ap-northeast-2.amazonaws.com/skteam01-delivery:latest
kubectl create deploy skteam01-product --image=496278789073.dkr.ecr.ap-northeast-2.amazonaws.com/skteam01-product:latest
kubectl create deploy skteam01-gateway --image=496278789073.dkr.ecr.ap-northeast-2.amazonaws.com/skteam01-gateway:latest

kubectl expose deploy skteam01-order --type=ClusterIP --port=8080
kubectl expose deploy skteam01-delivery --type=ClusterIP --port=8080
kubectl expose deploy skteam01-product --type=ClusterIP --port=8080


aws ecr create-repository --repository-name skteam01-order-test --region ap-northeast-2
aws ecr create-repository --repository-name skteam01-delivery-test --region ap-northeast-2
aws ecr create-repository --repository-name skteam01-product-test --region ap-northeast-2
aws ecr create-repository --repository-name skteam01-gateway-test --region ap-northeast-2



https://bscnote.tistory.com/105 imagepullbackoff

docker tag skteam01-order-test:latest 496278789073.dkr.ecr.ap-northeast-2.amazonaws.com/skteam01-order-test:latest
docker tag skteam01-delivery-test:latest 496278789073.dkr.ecr.ap-northeast-2.amazonaws.com/skteam01-delivery-test:latest
docker tag skteam01-product-test:latest 496278789073.dkr.ecr.ap-northeast-2.amazonaws.com/skteam01-product-test:latest
docker tag skteam01-gateway-test:latest 496278789073.dkr.ecr.ap-northeast-2.amazonaws.com/skteam01-gateway-test:latest


docker push 496278789073.dkr.ecr.ap-northeast-2.amazonaws.com/skteam01-order-test
docker push 496278789073.dkr.ecr.ap-northeast-2.amazonaws.com/skteam01-delivery-test
docker push 496278789073.dkr.ecr.ap-northeast-2.amazonaws.com/skteam01-product-test
docker push 496278789073.dkr.ecr.ap-northeast-2.amazonaws.com/skteam01-gateway-test

kubectl create deploy skteam01-order-test --image=496278789073.dkr.ecr.ap-northeast-2.amazonaws.com/skteam01-order-test:latest
kubectl create deploy skteam01-delivery-test --image=496278789073.dkr.ecr.ap-northeast-2.amazonaws.com/skteam01-delivery-test:latest
kubectl create deploy skteam01-product-test --image=496278789073.dkr.ecr.ap-northeast-2.amazonaws.com/skteam01-product-test:latest
kubectl create deploy skteam01-gateway-test --image=496278789073.dkr.ecr.ap-northeast-2.amazonaws.com/skteam01-gateway-test:latest
