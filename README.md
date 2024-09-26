# Reusable-可复用代码

接口文档路径
```
Knife4j UI: /doc.html
原始 Swagger UI: /swagger-ui.html
OpenAPI JSON: /v3/api-docs
OpenAPI YAML: /v3/api-docs.yaml
```
## Excel
### 导出
1.直接导出Excel：
    
    查询数据（可传入条件）- 构造Excel文件 - 导出Excel

## 文件处理
### 解析文件内容（Apache Tika）：

    上传文件 - Apache Tika解析文件内容 - 返回文件内容字符串
