<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<jsp:include page="/top.jsp" />

<style>
.img-responsive,
.thumbnail > img,
.thumbnail a > img,
.carousel-inner > .item > img,
.carousel-inner > .item > a > img {
  display: block;
	min-width:  330px;
	height : 570px;
	max-width: 330px;
}

</style>

<div class="section">
	<div class="container">
		<div class="col-md-12">
			<div class="col-md-4">
			<div id="fullcarousel-example" data-interval="false"
				class="carousel slide" data-ride="carousel">
				<div class="carousel-inner">
					<div class="item active">
						<img src="./images/1to50.png">
						<div class="carousel-caption">
							<h2>1 TO 50</h2>
							<p></p>
						</div>
					</div>
					<div class="item">
						<img src="./images/FlapJellyfish.png">
						<div class="carousel-caption">
							<h2>Flap Jellyfish</h2>
							<p></p>
						</div>
					</div>
					<div class="item">
						<img src="./images/HCJam.png">
						<div class="carousel-caption">
							<h2>HC Jam</h2>
							<p></p>
						</div>
					</div>
				</div>
				<a class="left carousel-control" href="#fullcarousel-example"
					data-slide="prev"><i class="icon-prev fa fa-angle-left"></i></a> <a
					class="right carousel-control" href="#fullcarousel-example"
					data-slide="next"><i class="icon-next fa fa-angle-right"></i></a>
			</div>
			</div>
			<div class="col-md-8">
			<iframe src="http://afree.ca/v0505/embed" width="700px" height="520px" frameborder="0" allowfullscreen="no" id="_afreeca" name="_afreeca" scrolling="no"></iframe>
			<div class="text-center">
				<ul class="nav navbar-nav">
				<li><a class="dropdown-item" href="http://afree.ca/v0505/embed" target="_afreeca" class="button">LIVE7</a></li>
				<li><a href="http://afree.ca/whdgus224/embed" target="_afreeca" class="button">AprilFool</a></li>
				<li><a href="http://afree.ca/xoqhgo/embed" target="_afreeca" class="button">오억만(烏億萬)</a></li>
				<li><a href="https://player.twitch.tv/?channel=kds9529" target="_afreeca" class="button">반달흑곰</a></li>
				<li><a href="https://player.twitch.tv/?channel=canonxg92" target="_afreeca" class="button">canonxg</a></li>		
				<li><a href="https://player.twitch.tv/?channel=tmxk319" target="_afreeca" class="button">괴물쥐</a></li>
				<li><a href="https://player.twitch.tv/?channel=handongsuk" target="_afreeca" class="button">한동숙</a></li>
				<!-- <li><a href="https://player.twitch.tv/?channel=yapyap30" target="_afreeca" class="button">얍얍</a></li>
				<li><a href="https://player.twitch.tv/?channel=hanryang1125" target="_afreeca" class="button">풍월량</a></li>
				<li><a href="https://player.twitch.tv/?channel=kanetv8" target="_afreeca" class="button">케인</a></li> -->
				</ul>
			</div>
			</div>
			</div>
		</div>
		

	</div>
</div>



<jsp:include page="/foot.jsp" />