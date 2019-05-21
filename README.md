# 警训旧系统

keyword | description
-|-
struts-.*?.xml | 定义了路由信息
BasasecAction.class | Contxt (Not POJO)，通过其中的map传递Model的数据
*。Action.class | controller，直接与视图打交道，并提供service
merge | 同步、合并实体，返回**新**实体 TODO:
update | 更新实体（不会返回新实体） TODO:
LEFT JOIN FETCH | The "fetch" tells hibernate to load it now instead of letting it be loaded lazily. TODO:
app | 流程流程
arrange | 不明(数据库无数据)
trainPlain | 训练计划，相当于业务主表

---

代码逻辑

> 代码 => 业务

两个流程的前后端代码都是独立的

1. 【培训项目流程】
   1. 更新/创建表单 `trainPlan/.../saveMyTrainPlan`
      1. 创建表单时创建流程(`AppCase`)，及第一个任务(`AppCaseTask`)
      2. 保存附件
   2. 提交审批（所有岗位通用）
      1. 预提交 `trainPlain/.../submitAudit`
         1. 列出可选的下一岗位
            1. 若当前环节是 `起草`
               - 对于角色为 市局管理员 和 政工办管理员 的用户，只能`反馈`
               - 对于角色为 科所队管理员 的用户，只能`提交单位审批`
            2. 其他岗位遵照流程的定义流转
      2. 提交审批 `trainPlain/.../executeSubmitAuit`
         1. 流转任务`APP_CASE_TASK`
            1. 更新且结束实例**所有**当前环节的任务
         2. 更新年度计划
            - 若已纳入年度计划 `is_year = 1`，并且审批阶段不是`草稿`或`不通过`，则更新年度计划进度（更新） （更新的逻辑过于冗长，暂时没找到。。。）
         3. 查找下一岗位的处理人
            1. = `map.get('userIds')`
            2. 若下一岗位的类型是指派，则下一处理人为所有被指派人(`PROCESS_USER`)
            3. 若下一环节是 `提交单位审批` 下一处理人 为当前登陆用户所在部门，及该部门其上的所有的特定人`DEP_USER_DEP`
               1. 查找用户所在的所有部门
               2. 添加部门所有角色为 政工办管理员 的用户
               3. 递归处理部门的上级部门
            4. 若下一环节是 `情况反馈`，下一处理人为 工单的当前办理人
         4. 为每个下一处理人创建任务
         5. （对于每个下一处理人，若在当前流程当前环节，没有其他代办任务）发送短信提醒待审核
         6. 若当前任务不是`草稿`，则对办理人进行站内提醒及短信提醒。若当前提交的是 `情况反馈`，则提示内容加上 `请于培训结束后及时反馈简要情况`。
         7. 上传附件
   3. 导入/导出Excel （略）
   4. 年度计划：即年度培训计划，不会对训练项目进行实际的人数/预算上的约束
      1. 年度计划的`使用预算`与`剩余预算`都是实时计算的出来的，数据库没有记录
2. 【e班审批流程】
   1. 预提交
      1. 列出可选的下一岗位
         1. 若当前环节是 `起草`
            - 对于角色为 市局管理员 和 政工办管理员 的用户，下一任务只能 `提交市局审批`
            - 对于角色为 科所队管理员 的用户，下一任务只能 `提交单位审批`
   2. 提交审批
      1. 与培训项目流程一样
3. e班管理
   1. 添加表单
      - e班与学员的关联表为 `EROOM_USER`
      - e班与课程的关联表为 `EROOM_USER_ECOURSE`
   2. 学员
   3. 课程类型
4. 资源管理
5. 通知管理

## PKI登陆

TODO:

登陆时，按下 Pki 登陆 将会跳转到Https的地址 `https://192.168.0.99/loginPki.action`，可能是一种根据电脑所拥有的证书（或者U盾）进行自动登陆/认证的机制 TODO:。现场服务器有监听HTTPS的443端口，其配置如下：

```xml
<maxThreads="512"
minSpareThreads="128"
maxSpareThreads="512"
acceptCount="1200"
URIEncoding="UTF-8"
connectionTimeout="20000"
enableLookups="false" SSLEnabled="true" scheme="https" secure="true"
   clientAuth="true" sslProtocol="TLS" 
keystoreFile="E:\ssl\10.40.59.189.jks" keystorePass="123456" truststoreFile="E:\ssl\cacerts" truststorePass="changeit"/>
```

由于拷贝代码时没有拷贝其中提及的目录/文件，因此于测试环境部署时没有启用443端口，若有需要可以联系现场同事拷贝后再启用

## 流程定义

1. 定义岗位
   - 名称
   - 同步/异步（无意义）
   - 步骤环节（用于判断是否需要初始化/结束流程）
   - 代办人类型（自主（主动报名，**未启用**），指派（仅能逐人指派），程序控制（通过后台代码通过`if ... else ...`写死控制））
   - 是否需要审核意见（是否多在办理任务界面多加一个【审核意见】的输入 TODO:）
2. 定义下一岗位（将岗位与岗位直接关联）
3. 设置指派（仅能逐人指派）

## 短信相关

详见`util.MobileUtil.class`

## 站内消息

## IP

TODO:

## 附件相关

旧系统所有附件均保存在 `...\Tomcat\webapps\Root\Upload` 中，已有数据大概有150G

1. 培训项目的工单附件 保存在 `JOBFILE` 表中，与主表通过ID关联
2. e班课程的资源 保存在 `STUFF` 表中，附件类型存在 `STUFF_TYPE` 表
3. 【财富论坛】下的音频存在 `AUDIO` 表，音频类型在 `AUDIO_TYPE` 表

## 已有流程

1. 培训项目流程

   ```mermaid
   graph TD
      起草-->市局
      起草-->单位
      单位-->市局
      单位-->通过
      单位-->不通过
      市局-->反馈
      市局-->通过
      单位-->市局报备
      市局-->不通过
      反馈-->通过
   ```

2. e班审批流程

 ```mermaid
 graph TD
    起草-->市局
    起草-->单位
    单位-->市局
    单位-->同意/不同意
    市局-->同意/不同意
 ```

- 起草
  - 单位
    - 市局
      - 同意/不同意
    - 同意/不同意
  - 市局
    - 同意/不同意
