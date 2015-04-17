<%@ page contentType="text/html; charset=utf-8"%>
<!DOCTYPE html>
<html>
 <head>
 <!-- <link href="../css/style.css" rel="stylesheet"> -->
  <style>
  
	#popup, #popup2, .bMulti {
		background-color: #fff;
		color: #111;
		display: none;
		min-width: 450px;
		padding: 25px;
	}
	
	#popup, .bMulti {
		min-height: 250px;
	}
	
	
	
	.button {
		background-color: #2b91af;
		color: #fff;
		cursor: pointer;
		display: inline-block;
		padding: 10px 20px;
		text-align: center;
		text-decoration: none;
	}
	.button:hover {
	  background-color: #296DB1;
	}
	.button.b-close, .button.bClose {
		/*border-radius: 7px 7px 7px 7px;*/
		box-shadow: none;
		font: bold 131% sans-serif;
		padding: 0 6px 2px;
		position: absolute;
		right: -7px;
		top: -7px;
	}
	
	/* 구문 추가 설정 */
	pre {
		display: block;
		padding: 9.5px;
		margin: 20px 0;
		font-size: 13px;
		line-height: 1.42857143;
		color: #333;
		word-break: break-all;
		word-wrap: break-word;
		background-color: #f5f5f5;
		border: 1px solid #ccc;
		border-radius: 4px;
		font-family: Menlo,Monaco,Consolas,"Courier New",monospace;
	}
  </style>
 
     <!-- Latest compiled and minified CSS -->
	<link rel="stylesheet" href="<%= request.getContextPath() %>/css/jquery-ui-1.10.3.custom.css" />
	<link rel="stylesheet" href="<%= request.getContextPath() %>/css/bootstrap.min.css">
	<!-- Latest compiled JavaScript -->
	<script type="text/javascript" src="<%= request.getContextPath() %>/js/jquery-1.9.1.js"></script>
	<script type="text/javascript" src="<%= request.getContextPath() %>/js/jquery-ui-1.10.3.custom.min.js"></script>
	<script type="text/javascript" src="<%= request.getContextPath() %>/js/bootstrap.min.js"></script>
	<script type="text/javascript" src="<%= request.getContextPath() %>/js/addys.js"></script>
	<script type="text/javascript" src="<%= request.getContextPath() %>/js/jquery.number.js"></script>
	
	
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
<script src="<%= request.getContextPath() %>/js/jquery.bpopup.min.js"></script>
<script src="http:////cdnjs.cloudflare.com/ajax/libs/jquery-easing/1.3/jquery.easing.min.js"></script>

	<script>
	
	;(function($) {
	   	$(function() {
	   		var $p1 = $('#popup'),
	   		    $p2 = $('#popup2');
	   		    // i = 0;

	       	$('body').on('click', '.small', function(e) {
	           	e.preventDefault();
	           	var popup = $(this).hasClass('pop1') ? $p1 : $p2,
	           	    content = $('.content'),
	           	    self = $(this);

	           	popup.bPopup(self.data('bpopup') || {});
			    });

	    });
	})(jQuery);

	</script>
  </head>
  <body>
   <div class="container">
<span class="button small pop1">Example 1:default</span>
<pre>
$('element_to_pop_up').bPopup();
</pre>

<span class="button small pop2" data-bpopup='{"contentContainer":".content","loadUrl":"<%= request.getContextPath() %>/gmroiclc"}'>Example 5a{"contentContainer":".content","loadUrl":"test.html"}</span>
<pre>
$('element_to_pop_up').bPopup({
    follow: [false, false], //x, y
    position: [150, 400] //x, y
});
</pre>		
<span class="button small pop1" data-bpopup='{"modalClose":false,"opacity":0.6,"positionStyle":"fixed"}'>Example 2b{"modalClose":false,"opacity":0.6,"positionStyle":"fixed"}</span>
<pre>
$('element_to_pop_up').bPopup({
    modalClose: false,
    opacity: 0.6,
    positionStyle: 'fixed' //'fixed' or 'absolute'
});
</pre>

<span class="button small pop1" data-bpopup='{"fadeSpeed":"slow", "followSpeed":1500, "modalColor":"greenYellow"}'>Example 2c(fadeSpeed,followSpeed,modalColor)</span>
<pre>
$('element_to_pop_up').bPopup({
    fadeSpeed: 'slow', //can be a string ('slow'/'fast') or int
    followSpeed: 1500, //can be a string ('slow'/'fast') or int
    modalColor: 'greenYellow'
});
</pre>
<br>
<br>

<span class="button small pop1" data-bpopup='{"transition":"slideDown","speed":850,"easing":"easeOutBack"}'>Example 3a{"transition":"slideDown","speed":850,"easing":"easeOutBack"}</span>
<pre>
$('element_to_pop_up').bPopup({
    easing: 'easeOutBack', //uses jQuery easing plugin
    speed: 450,
    transition: 'slideDown'
});
</pre>

<span class="button small pop1" data-bpopup='{"transition":"slideIn","transitionClose": "slideBack","speed":650}'>Example 3b{"transition":"slideIn","transitionClose": "slideBack","speed":650}</span>
<pre>
$('element_to_pop_up').bPopup({
    speed: 650,
    transition: 'slideIn',
    transitionClose: 'slideBack'
});
</pre>

<br>
<br>

<span class="button small pop2" data-bpopup='{"contentContainer":".content","loadUrl":"test.html"}'>Example 5a{"contentContainer":".content","loadUrl":"test.html"}</span>
<pre>
$('element_to_pop_up').bPopup({
    contentContainer:'.content',
    loadUrl: 'test.html' //Uses jQuery.load()
});
</pre>

<span class="button small pop2" data-bpopup='{"content":"image","contentContainer":".content","loadUrl":"../images/image.jpg"}'>Example 5b{"content":"image","contentContainer":".content","loadUrl":"../images/image.jpg"}</span>
<pre>
$('element_to_pop_up').bPopup({
    content:'image', //'ajax', 'iframe' or 'image'
    contentContainer:'.content',
    loadUrl:'image.jpg'
});
</pre>

<span class="button small pop2" data-bpopup='{"content":"iframe","contentContainer":".content","loadUrl":"http://demun.tistory.com"}'>Example 5c{"content":"iframe","contentContainer":".content","loadUrl":"http://demun.tistory.com"}</span>
<pre>
$('element_to_pop_up').bPopup({
    content:'iframe', //'ajax', 'iframe' or 'image'
    contentContainer:'.content',
    loadUrl:'http://dinbror.dk/blog' //Uses jQuery.load()
});
</pre>
		
		
		<div id="popup">
		    <span class="button b-close"><span>X</span></span>
		    <p>처음부터 팝업 문서에 포함되어 있는 div - 클릭시 레이어로 나옴.</p>
		</div>
		<div id="popup2">
		    <span class="button b-close"><span>X</span></span>
		    <div class="content"></div>
		</div>
	</div>
  </body>
</html>
