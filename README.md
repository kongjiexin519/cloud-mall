# cloud-mall

- 该微商城系统是一个基于微服务的B2C电商系统，有3个业务子模块，分别是用户模块、商品和商品分类模块、购物车和订单模块。
- 技术架构上选择的SpringCloud + Eureka微服务架构体系，使用Zuul网关模块实现统一请求入口和实现权限控制，采用Feign进行服务之间的调用，并且融合了Swagger、ZXing等技术。
- 该微商城使用了MySQL数据库，并使用Redis实现Session共享以及缓存商品目录等信息缓存加速响应。
