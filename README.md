# Zero-Tablesaw
Zero-Tablesaw

# 1. 概述
## 1.1. 背景
Tablesaw是一款Java的数据可视化库，主要包括两部分：
  - 数据解析库，主要用于加载数据，对数据进行操作(转化，过滤，汇总等)，类比Python中的Pandas库；
  - 数据可视化库，将目标数据转化为可视化的图表，类比Python中的Matplotlib库。

与Pandas不同的是，Tablesaw中的表格以列(Column)为基本单位，因此大部分操作都是基于列进行的。当然也包括部分对行操作的函数，但是功能比较有限

## 1.2. 说明
1. aggregate：maven 的项目父级项目，主要定义项目打包的配置。 
2. beakerx：tablesaw 库的注册中心，主要注册表和列。 
3. core：tablesaw 库的核心代码，主要是数据的加工处理操作：数据的追加，排序，分组，查询等。 
4. data：项目测试数据目录。 
5. docs：项目 MarkDown 文档目录。 
6. docs-src：项目文档源码目录，主要作用是生成 MarkDown 文档。 
7. excel：解析 excel 文件数据的子项目。 
8. html：解析 html 文件数据的子项目。 
9. json：解析 json 文件数据的子项目。 
10. jsplot：数据可视化的子项目，主要作用加载数据生成可视化图表。 
11. saw：tablesaw 读写图表数据的子项目。


# 2. 功能

# 3. 使用

# 4. 其他

# 5. 参考