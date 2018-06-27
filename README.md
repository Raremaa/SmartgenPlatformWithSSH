#众智协作平台后台(基于SSH框架+IDEA制作)
<h3>众智协作平台后台(基于SSH框架+IDEA制作)</h3>
<b>本次项目收获:</b>
<ol>
    <li>最重要一点 不要拿到手就干 要想清楚 想仔细 在开始动手 不要做无用功</li>
    <li>一个action注入serviceDao（实例）后共用一个session 在先查后删的业务上 "查会生成一个id" "前台传会生成一个id" 这里你调delete时候一定要注意,调查生成的id,这样至始至终session里这个id所标记的对象只有一个不会报错,调前台传的对象删除会造成session中有两个id相同的对象,会报错</li>
    <li>务必分清service和Action,需要事务环境一定要放在service进行事务管理</li>
    <li>多对一 多对多关系的保存一定注意 只要不设置级联是不会影响关联表的数据的,所以只要通过id就可以保存或修改(即使给了除了id以外的数据与数据库中不同 也不会影响关联表的数据 只会用到id),而不需要把所有数据都丢过来(比如存用户表与购物车表,如果需要存购物车,只需要新建一个user对象 id为制定值 而不需要把所有值都丢过来)</li>
    <li>以后的项目按照功能模块划分</li>
    <li>以后的项目一定要有BaseXX 来继承 会省去大量重复代码</li>
    <li>以后的项目要有QueryParam 与 pojo.view</li>
    <li>遇到"多对多"的关系,考虑要不要按照 "列表-详情表" 这样的设计转变为一对多,可以加入一些冗余字段减少查询数量,当然缺点也是显而易见的,如果关联表更新,这部分冗余字段没有被维护</li>
    <li>遇到"一对一关系" 考虑要不要将其中一个表丢到另一个表中,以达到减少一个表的目的,这个要视情况而定</li>
    <li>删除只和id相关</li>
    <li>修改三个思路：1.前台传所有数据 2.后台只修改非null字段 (HQL:update Teacher t set t.name = 'admin' where id = 3) 3."先查后改" 先从持久层拿数据对比前台传的数据改完送回持久层</li>
    <li>批量操作效率较低 需要研究优化算法</li>
    <li>写批量操作之前 先写单操作方法 再批量调用 降低耦合 提高内聚</li>
</ol>
<b>很多地方设计有问题 很多地方应该丢在事务里 后续优化</b><br>
<b>本项目在原有servlet项目的数据库进行了相关修改</b>
<ul>
    <li>product</li>
    <li>purchase</li> 
    <li>productevalute</li> 
    <li>shoppingcart</li> 
    <li>purchaseaddress</li> 
    <li>collectionInfo</li> 
    <li>browsinghistory</li>
</ul>

