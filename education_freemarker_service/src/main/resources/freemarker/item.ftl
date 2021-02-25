<!DOCTYPE html>
<html>

<head>
	<meta charset="utf-8" />
	<meta http-equiv="X-UA-Compatible" content="IE=9; IE=8; IE=7; IE=EDGE">
	<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7" />
	<title>产品详情页</title>
	 <link rel="icon" href="assets/img/favicon.ico">

    <link rel="stylesheet" type="text/css" href="css/webbase.css" />
    <link rel="stylesheet" type="text/css" href="css/pages-item.css" />
    <link rel="stylesheet" type="text/css" href="css/pages-zoom.css" />
    <link rel="stylesheet" type="text/css" href="css/widget-cartPanelView.css" />
</head>

<body>

<!--页面顶部 开始-->
<div id="nav-bottom">
	<!--顶部-->
	<div class="nav-top">
		<div class="top">
			<div class="py-container">
				<div class="shortcut">
					<ul class="fl">
						<li class="f-item">在线教育欢迎您！</li>
						<li class="f-item">请<a href="login.html" target="_blank">登录</a>　<span><a href="register.html" target="_blank">免费注册</a></span></li>
					</ul>
					<ul class="fr">
						<li class="f-item">我的订单</li>
						<li class="f-item space"></li>
						<li class="f-item"><a href="home.html" target="_blank">我的课程</a></li>
						<li class="f-item space"></li>
						<li class="f-item">在线教育会员</li>
						<li class="f-item space"></li>
						<li class="f-item">企业采购</li>
						<li class="f-item space"></li>
						<li class="f-item">关注网站</li>
						<li class="f-item space"></li>
						<li class="f-item" id="service">
							<span>客户服务</span>
							<ul class="service">
								<li><a href="cooperation.html" target="_blank">合作招商</a></li>
								<li><a href="shoplogin.html" target="_blank">商家后台</a></li>
								<li><a href="cooperation.html" target="_blank">教师合作</a></li>
								<li><a href="#">教师后台</a></li>
							</ul>
						</li>
						<li class="f-item space"></li>
						<li class="f-item">网站导航</li>
					</ul>
				</div>
			</div>
		</div>

		<!--头部-->
		<div class="header">
			<div class="py-container">
				<div class="yui3-g Logo">
					<div class="yui3-u Left logoArea">
						<a class="logo-bd" title="在线教育" href="JD-index.html" target="_blank"></a>
					</div>
					<div class="yui3-u Center searchArea">
						<div class="search">
							<form action="" class="sui-form form-inline">
								<!--searchAutoComplete-->
								<div class="input-append">
									<input type="text" id="autocomplete" type="text" class="input-error input-xxlarge" />
									<button class="sui-btn btn-xlarge btn-danger" type="button">搜索</button>
								</div>
							</form>
						</div>
						
					</div>
					<div class="yui3-u Right shopArea">
						<div class="fr shopcar">
							<div class="show-shopcar" id="shopcar">
								<span class="car"></span>
								<a class="sui-btn btn-default btn-xlarge" href="cart.html" target="_blank">
									<span>我的购物车</span>
									<i class="shopnum">0</i>
								</a>
						
							</div>
						</div>
					</div>
				</div>

				<div class="yui3-g NavList">
					<div class="yui3-u Left all-sort">
						<h4>全部商品分类</h4>
					</div>
					<div class="yui3-u Center navArea">
						<ul class="nav">
							<li class="f-item">前端开发</li>
							<li class="f-item">后端开发</li>
							<li class="f-item">移动开发</li>
							<li class="f-item">计算机基础</li>
							<li class="f-item"><a href="seckill-index.html" style="text-decoration: none" target="_blank">我的推荐</a></li>
							<li class="f-item"><a href="seckill-index.html" style="text-decoration: none" target="_blank">限时秒杀</a></li>
						</ul>
					</div>
					<div class="yui3-u Right"></div>
				</div>
			</div>
		</div>
	</div>
</div>
<!--页面顶部 结束-->
	<div class="py-container">
		<div id="item">
			<div class="crumb-wrap">
				<ul class="sui-breadcrumb">
					<li>
						<a href="#">面包屑</a>
					</li>
					<li>
						<a href="#">面包屑</a>
					</li>
					<li>
						<a href="#">面包屑</a>
					</li>
					<li class="active">java后台开发</li>
				</ul>
			</div>
			<!--product-info-->
			<div class="product-info">
				<div class="fl preview-wrap">
					<!--放大镜效果-->
					<div class="zoom">
						<!--默认第一个预览-->
						<div id="preview" class="spec-preview">
							<span class="jqzoom"><img jqimg="${course.image}" src="${course.image}" style="width:400px; height:400px"  /></span>
						</div>
						<!--下方的缩略图-->
						
					</div>
				</div>
				<div class="fr itemInfo-wrap">
					<div class="sku-name">
						<h4>${course.courseName}</h4>
					</div>
					<div class="news"><span>希望你逢考必过，，希望你逢考必过，希望你逢考必过，</span></div>
					<div class="summary">
						<div class="summary-wrap">
							<div class="fl title">
								<i>价　　格</i>
							</div>
							<div class="fl price">
								<i>¥</i>
								<em>${course.price}</em>
								<span>降价通知</span>
							</div>
							<div class="fr remark">
								<i>累计评价</i><em>12345</em>
							</div>
						</div>
						<div class="summary-wrap">
							<div class="fl title">
								<i>促　　销</i>
							</div>
							<div class="fl fix-width">
								<i class="red-bg">加价购</i>
								<em class="t-gray">鹤云堂，改运让你更成功。鹤云堂，为你的好运而生。</em>
							</div>
						</div>
					</div>
					<div class="support">
						
						<div class="summary-wrap">
							<div class="fl title">
								<i>配 送 至</i>
							</div>
							<div class="fl fix-width">
								<em class="t-gray">满999.00另加20.00元，或满1999.00另加30.00元，或满2999.00另加40.00元，即可在购物车换购热销商品</em>
							</div>
						</div>
					</div>
					<div class="clearfix choose">
						<div id="specification" class="summary-wrap clearfix">
							
						
	
							<dl>
								<dt>
									<div class="fl title">
									<i> <p><span><h3>综合素质基础知识</h3></span></p></i>
								</div>
								</dt>
								<p><span>课程小节：${courseDescVO.teachServiceTime}小节</span></p>
								<span>时长：${courseDescVO.videoTime}小时</span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span style="font-size:20px;color:red">${courseDescVO.peopleCounting}人在学</span>
							
							</dl>
							
							
						</div>
					
						<div class="summary-wrap">
							<div class="fl title">
								<div class="control-group">
							
								</div>
							</div>
							<div class="fl">
								<ul class="btn-choose unstyled">
									<li>
										<a href="javascript:getCartList()"  class="sui-btn  btn-danger addshopcar">加入购物车</a>
									</li>
									<li>
										<button id="pause" class="sui-btn  btn-danger addshopcar" onclick="playPause()">免费试学</button>
									</li>
									<li>
										<a onclick="tradeInfo()" class="sui-btn  btn-danger addshopcar">立即购买</a>
									</li>
								</ul>
							</div>
							<div style="text-align:center;">

								<button onclick="makeBig()">大</button>
								<button onclick="makeNormal()">中</button>
								<button onclick="makeSmall()">小</button>
								<br/>
								<video id="video1" width="420" style="margin-top:15px;">
									<source src="https://shdongdongshop.oss-cn-beijing.aliyuncs.com/1611988794550advideo.mp4" type="video/mp4">
								</video>
							</div>
							<script type="text/javascript">
								function run(){
									myVideo.pause();
									document.getElementById("pause").disabled=true;
									alert("试看结束,请购买该课程");
								}
							</script>
							<script type="text/javascript">
								var myVideo=document.getElementById("video1");
								function playPause(){
									if (myVideo.paused){
										myVideo.play();
										setTimeout("run()", 12000);
									}
									else{
										myVideo.pause();
									}
								}
								function makeBig(){
									myVideo.width=560;
								}
								function makeSmall(){
									myVideo.width=320;
								}
								function makeNormal(){
									myVideo.width=420;
								}
							</script>
						</div>
					</div>
				</div>
			</div>
			<!--product-detail-->
			<div class="clearfix product-detail">
				<div class="fl aside">
					<ul class="sui-nav nav-tabs tab-wraped">
						<li class="active">
							<a href="#index" data-toggle="tab">
								<span>相关课程</span>
							</a>
						</li>
						<li>
							<a href="#profile" data-toggle="tab">
								<span>推荐课程</span>
							</a>
						</li>
					</ul>
					<div class="tab-content tab-wraped">
						<div id="index" class="tab-pane active">
							<ul class="part-list unstyled">
								<li>java后台开发</li>
								<li>HTML</li>
								<li>Redis</li>
								<li>平板电脑java</li>
							</ul>
						</div>
						<div id="profile" class="tab-pane">
							<p>推荐课程</p>
						</div>
					</div>
				</div>
				<div class="fr detail">

					<div class="tab-main intro">
						<ul class="sui-nav nav-tabs tab-wraped">
							<li class="active">
								<a href="#one" data-toggle="tab">
									<span>课程介绍</span>
								</a>
							</li>
							<li>
								<a href="#two" data-toggle="tab">
									<span>讲师介绍</span>
								</a>
							</li>
							<li>
								<a href="#three" data-toggle="tab">
									<span>课程章节</span>
								</a>
							</li>
							<li>
								<a href="#four" data-toggle="tab">
									<span>课程评价</span>
								</a>
							</li>
							<li>
								<a href="#five" data-toggle="tab">
									<span>学圈</span>
								</a>
							</li>
						</ul>
						<div class="clearfix"></div>
						<div class="tab-content tab-wraped">
							<div id="one" class="tab-pane active">
								${course.courseIntroduction}
							</div>
							<div id="two" class="tab-pane">
								<span>${teacherVO.brief}</span>
							</div>
							<div id="three" class="tab-pane">
								<img style="height:300px;width:850px" src="img/section.png">
							</div>
							<div id="four" class="tab-pane">
								<input type="hidden" id="courseId">
								<span onclick="showComment()">课程评价</span>
							</div>
							<div id="five" class="tab-pane">
								<p>学圈</p>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- 底部栏位 -->
	
<!--页面底部  开始 -->
<div class="clearfix footer">
	<div class="py-container">
		<div class="footlink">
			<div class="Mod-service">
				<ul class="Mod-Service-list">
					<li class="grid-service-item intro  intro1">

						<i class="serivce-item fl"></i>
						<div class="service-text">
							<h4>正品保障</h4>
							<p>正品保障，提供发票</p>
						</div>

					</li>
					<li class="grid-service-item  intro intro2">

						<i class="serivce-item fl"></i>
						<div class="service-text">
							<h4>正品保障</h4>
							<p>正品保障，提供发票</p>
						</div>

					</li>
					<li class="grid-service-item intro  intro3">

						<i class="serivce-item fl"></i>
						<div class="service-text">
							<h4>正品保障</h4>
							<p>正品保障，提供发票</p>
						</div>

					</li>
					<li class="grid-service-item  intro intro4">

						<i class="serivce-item fl"></i>
						<div class="service-text">
							<h4>正品保障</h4>
							<p>正品保障，提供发票</p>
						</div>

					</li>
					<li class="grid-service-item intro intro5">

						<i class="serivce-item fl"></i>
						<div class="service-text">
							<h4>正品保障</h4>
							<p>正品保障，提供发票</p>
						</div>

					</li>
				</ul>
			</div>
			<div class="clearfix Mod-list">
				<div class="yui3-g">
					<div class="yui3-u-1-6">
						<h4>购物指南</h4>
						<ul class="unstyled">
							<li>购物流程</li>
							<li>会员介绍</li>
							<li>生活旅行/团购</li>
							<li>常见问题</li>
							<li>购物指南</li>
						</ul>

					</div>
					<div class="yui3-u-1-6">
						<h4>配送方式</h4>
						<ul class="unstyled">
							<li>上门自提</li>
							<li>211限时达</li>
							<li>配送服务查询</li>
							<li>配送费收取标准</li>
							<li>海外配送</li>
						</ul>
					</div>
					<div class="yui3-u-1-6">
						<h4>支付方式</h4>
						<ul class="unstyled">
							<li>货到付款</li>
							<li>在线支付</li>
							<li>分期付款</li>
							<li>邮局汇款</li>
							<li>公司转账</li>
						</ul>
					</div>
					<div class="yui3-u-1-6">
						<h4>售后服务</h4>
						<ul class="unstyled">
							<li>售后政策</li>
							<li>价格保护</li>
							<li>退款说明</li>
							<li>返修/退换货</li>
							<li>取消订单</li>
						</ul>
					</div>
					<div class="yui3-u-1-6">
						<h4>特色服务</h4>
						<ul class="unstyled">
							<li>夺宝岛</li>
							<li>DIY装机</li>
							<li>延保服务</li>
							<li>在线教育E卡</li>
							<li>在线教育通信</li>
						</ul>
					</div>
					<div class="yui3-u-1-6">
						<h4>帮助中心</h4>
						<img src="img/wx_cz.jpg">
					</div>
				</div>
			</div>
			<div class="Mod-copyright">
				<ul class="helpLink">
					<li>关于我们<span class="space"></span></li>
					<li>联系我们<span class="space"></span></li>
					<li>关于我们<span class="space"></span></li>
					<li>商家入驻<span class="space"></span></li>
					<li>营销中心<span class="space"></span></li>
					<li>友情链接<span class="space"></span></li>
					<li>关于我们<span class="space"></span></li>
					<li>营销中心<span class="space"></span></li>
					<li>友情链接<span class="space"></span></li>
					<li>关于我们</li>
				</ul>
				<p>地址：北京市昌平区沙阳路18号 邮编：100000 电话：400-0123-302</p>
				<p>京ICP备08006666号京公网安备11010888888</p>
			</div>
		</div>
	</div>
</div>

	
	<!--侧栏面板开始-->
<div class="J-global-toolbar">
	<div class="toolbar-wrap J-wrap">
		<div class="toolbar">
			<div class="toolbar-panels J-panel">

				<!-- 购物车 -->
				<div style="visibility: hidden;" class="J-content toolbar-panel tbar-panel-cart toolbar-animate-out">
					<h3 class="tbar-panel-header J-panel-header">
						<a href="" class="title"><i></i><em class="title">购物车</em></a>
						<span class="close-panel J-close" onclick="cartPanelView.tbar_panel_close('cart');" ></span>
					</h3>
					<div class="tbar-panel-main">
						<div class="tbar-panel-content J-panel-content">
							<div id="J-cart-tips" class="tbar-tipbox hide">
								<div class="tip-inner">
									<span class="tip-text">还没有登录，登录后商品将被保存</span>
									<a href="#none" class="tip-btn J-login">登录</a>
								</div>
							</div>
							<div id="J-cart-render">
								<!-- 列表 -->
								<div id="cart-list" class="tbar-cart-list">
								</div>
							</div>
						</div>
					</div>
					<!-- 小计 -->
					<div id="cart-footer" class="tbar-panel-footer J-panel-footer">
						<div class="tbar-checkout">
							<div class="jtc-number"> <strong class="J-count" id="cart-number">0</strong>件商品 </div>
							<div class="jtc-sum"> 共计：<strong class="J-total" id="cart-sum">¥0</strong> </div>
							<a class="jtc-btn J-btn" href="#none" target="_blank">去购物车结算</a>
						</div>
					</div>
				</div>

				<!-- 我的关注 -->
				<div style="visibility: hidden;" data-name="follow" class="J-content toolbar-panel tbar-panel-follow">
					<h3 class="tbar-panel-header J-panel-header">
						<a href="#" target="_blank" class="title"> <i></i> <em class="title">我的关注</em> </a>
						<span class="close-panel J-close" onclick="cartPanelView.tbar_panel_close('follow');"></span>
					</h3>
					<div class="tbar-panel-main">
						<div class="tbar-panel-content J-panel-content">
							<div class="tbar-tipbox2">
								<div class="tip-inner"> <i class="i-loading"></i> </div>
							</div>
						</div>
					</div>
					<div class="tbar-panel-footer J-panel-footer"></div>
				</div>

				<!-- 我的足迹 -->
				<div style="visibility: hidden;" class="J-content toolbar-panel tbar-panel-history toolbar-animate-in">
					<h3 class="tbar-panel-header J-panel-header">
						<a href="#" target="_blank" class="title"> <i></i> <em class="title">我的足迹</em> </a>
						<span class="close-panel J-close" onclick="cartPanelView.tbar_panel_close('history');"></span>
					</h3>
					<div class="tbar-panel-main">
						<div class="tbar-panel-content J-panel-content">
							<div class="jt-history-wrap">
								<ul>
									<!--<li class="jth-item">
										<a href="#" class="img-wrap"> <img src=".portal/img/like_03.png" height="100" width="100" /> </a>
										<a class="add-cart-button" href="#" target="_blank">加入购物车</a>
										<a href="#" target="_blank" class="price">￥498.00</a>
									</li>
									<li class="jth-item">
										<a href="#" class="img-wrap"> <img src="portal/img/like_02.png" height="100" width="100" /></a>
										<a class="add-cart-button" href="#" target="_blank">加入购物车</a>
										<a href="#" target="_blank" class="price">￥498.00</a>
									</li>-->
								</ul>
								<a href="#" class="history-bottom-more" target="_blank">查看更多足迹商品 &gt;&gt;</a>
							</div>
						</div>
					</div>
					<div class="tbar-panel-footer J-panel-footer"></div>
				</div>

			</div>

			<div class="toolbar-header"></div>

			<!-- 侧栏按钮 -->
			<div class="toolbar-tabs J-tab">
				<div onclick="cartPanelView.tabItemClick('cart')" class="toolbar-tab tbar-tab-cart" data="购物车" tag="cart" >
					<i class="tab-ico"></i>
					<em class="tab-text"></em>
					<span class="tab-sub J-count " id="tab-sub-cart-count">0</span>
				</div>
				<div onclick="cartPanelView.tabItemClick('follow')" class="toolbar-tab tbar-tab-follow" data="我的关注" tag="follow" >
					<i class="tab-ico"></i>
					<em class="tab-text"></em>
					<span class="tab-sub J-count hide">0</span>
				</div>
				<div onclick="cartPanelView.tabItemClick('history')" class="toolbar-tab tbar-tab-history" data="我的足迹" tag="history" >
					<i class="tab-ico"></i>
					<em class="tab-text"></em>
					<span class="tab-sub J-count hide">0</span>
				</div>
			</div>

			<div class="toolbar-footer">
				<div class="toolbar-tab tbar-tab-top" > <a href="#"> <i class="tab-ico  "></i> <em class="footer-tab-text">顶部</em> </a> </div>
				<div class="toolbar-tab tbar-tab-feedback" > <a href="#" target="_blank"> <i class="tab-ico"></i> <em class="footer-tab-text ">反馈</em> </a> </div>
			</div>

			<div class="toolbar-mini"></div>
			<input type="hidden" id = "priceInfo" value="${course.price};">
		</div>

		<div id="J-toolbar-load-hook"></div>

	</div>
</div>

<script type="text/template" id="tbar-cart-item-template">
	<div class="tbar-cart-item" >
		<div class="jtc-item-promo">
			<em class="promo-tag promo-mz">满赠<i class="arrow"></i></em>
			<div class="promo-text">已购满600元，您可领赠品</div>
		</div>
		<div class="jtc-item-goods">
			<span class="p-img"><a href="#" target="_blank"><img src="{2}" alt="{1}" height="50" width="50" /></a></span>
			<div class="p-name">
				<a href="#">{1}</a>
			</div>
			<div class="p-price"><strong>¥{3}</strong>×{4} </div>
			<a href="#none" class="p-del J-del">删除</a>
		</div>
	</div>
</script>
<script type="text/javascript" src="js/plugins/jquery/jquery.min.js"></script>
<script type="text/javascript">
$(function(){
	$("#service").hover(function(){
		$(".service").show();
	},function(){
		$(".service").hide();
	});
	$("#shopcar").hover(function(){
		$("#shopcarlist").show();
	},function(){
		$("#shopcarlist").hide();
	});

})
</script>
<script type="text/javascript" src="js/model/cartModel.js"></script>
<script type="text/javascript" src="js/plugins/jquery.easing/jquery.easing.min.js"></script>
<script type="text/javascript" src="js/plugins/sui/sui.min.js"></script>
<script type="text/javascript" src="js/plugins/jquery.jqzoom/jquery.jqzoom.js"></script>
<script type="text/javascript" src="js/plugins/jquery.jqzoom/zoom.js"></script>
<script type="text/javascript" src="index/index.js"></script>


<!--页面底部  结束 -->
</body>

</html>
<script type="text/javascript" >

	/*加入购物车*/
	function getCartList(){
		// 获取sku主键 商品数量
		var id = '${course.id?c}';
		//alert(id);
		location.href = "http://localhost:10010/cartController/addCart/"+id;
	}
	function showComment(obj){
		$.ajax({
			url : "http://localhost:10010/commentController/showComment",
			type : "post",
			dataType : "json",
			data : {pageNumber:$("#pageNumber").val(),pageSize:$("#pageSize").val(),courseId:courseId,like:obj},
			async : false,
			success : function (page){
				pageLL(page);
				var result = page.data.result;
				var content = "";
				for (var i = 0;i < result.length; i++){
					var num = result[i].id;
					content += '<div class="cart-item-list" >';
					content += '<div class="cart-shop">';
					content += '<span class="shopname self">id:'+ num +'</span><br>';
					content += '<span class="shopname self">创建时间:'+ result[i].createTime +'</span><br>';
					content += '<span class="shopname self">标题:'+ result[i].title +'</span><br>';
					content += '<span class="shopname self">评论:'+ result[i].comment +'</span><br>';
					content += '<span class="shopname self">星星:'+ result[i].level +'</span><br>';
					content += '<input type="text" id="backMsg'+ num +'"><br>';
					//content += '<input type="button" value="回复" onclick="backMsg('+ num +')"><br>';
					content += '<input type="button" value="回复" onclick="backMsg(\''+ num +'\')"><br>';
					content += '</div>';
					content += '</div>';

					$.ajax({
						url :"http://localhost:10010/commentController/backMsgGet",
						type : "post",
						dataType : "json",
						data : {id:result[i].id},
						async : false,
						success : function (pageTwo){
							console.log(pageTwo);
							for (var j = 0;j < pageTwo.length; j++){
								content += '<div class="cart-item-list" >';
								content += '<div class="cart-shop">';
								content += '<span class="shopname self">id:'+ pageTwo[j].data.id +'</span><br>';
								content += '<span class="shopname self">创建时间:'+ pageTwo[j].data.createTime +'</span><br>';
								content += '<span class="shopname self">评论:'+ pageTwo[j].data.comment +'</span><br>';
								content += '</div>';
								content += '</div>';
							}
						},
						error : function (result){

						}
					})

					content += '<br><br><br><br>';
				}

				$("#comm").html(content);
			},
			error : function (result){
				alert("失败2");
			}
		})
	}

	function backMsg(id){
		var comment = $("#backMsg"+id).val();
		//alert(comment);
		$.ajax({
			url :"http://localhost:10010/commentController/backMsg",
			type : "post",
			dataType : "json",
			data : {id:id,comment:comment},
			async : true,
			success : function (page){
				location.reload();
				alert("success");
			},
			error : function (result){

			}
		})
		var courseId = 149187842867914;
		//var courseId = 149,187,842,867,914;
		//var itemId = $("[name=goodsIdCart1]").val();
		//var num = $("[name=numInfo1]").val();
		location.href = "http://localhost:10010/cartController/addCart/"+courseId;
	}

	// 生成订单跳转支付
	function tradeInfo(){
		let price =$("#priceInfo").val();
		$.ajax({
			url : "http://localhost:10010/order/addOrderInfo",
			type : "post",
			dataType : "json",
			data:{price:price},
			async : false,
			success : function (result){
				let map = result.data;
				// 获取地址
				location.href = "http://localhost:10010/alipay/toPayInfo?out_trade_no=" + map.out_trade_no +"&body=" + map.body + "&total_amount=" + map.total_amount + "&subject=" + map.subject + "&username=" + map.username;
			},
			error : function (){
				alert("网络异常");
			}
		})
	}
</script>


