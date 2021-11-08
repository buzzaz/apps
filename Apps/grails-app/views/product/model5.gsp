<html>
<title>
Pixel 2.0 API 
</title>
<head>
<style>
body{
  background-color: #ccece9;
  color: #1F618D;
  font-family: calibri;
  margin: 4%
}
.mainHeaderH1{
  color: #7B241C;
  text-shadow: 1px 1px #F8F9F9;
}
.canvasRoundCorders{
  border-radius: 10px;
  background: #F4F6F6;
  box-shadow: 2px 2px #ABB2B9;
  padding: 3px; 
  width: 300px;
  height: 150px; 
}

</style>
<script src='https://www.dukelearntoprogram.com/course1/common/js/image/SimpleImage.js'></script>
<script>
window.CP.PenTimer.MAX_TIME_IN_LOOP_WO_EXIT = 3000;

var image = null;

function drawImageByAlgorithm(elem){
  var canvas = document.getElementById(elem);
  var output = new SimpleImage(canvas.width,canvas.height);
  var xmax = canvas.width - 1;
  var ymax = canvas.height - 1;
  var xmargin = 5;
  var ymargin = 5;
  var step = 5;

  for (var po of output.values()){
    var x = po.getX();
    var y = po.getY();
    if (y < ymax/2){
      if (y == xmargin && x >= xmargin && x <= xmax - xmargin){po.setGreen(255)}
      if(y == xmargin && x == xmax){xmargin = xmargin + step}
    }else if(y == ymax/2 && x === 0){
      xmargin = Math.floor(ymax/2) - step;
    }else{
      if(y == ymax - xmargin && x >= xmargin && x <= xmax - xmargin){po.setGreen(255)}
      if(y == ymax - xmargin && x == xmax){xmargin = xmargin - step}
    }
    if( (x%step === 0 && x/step >= 1) && (x < xmargin || x > xmax - xmargin)){po.setGreen(255)}
  }
  output.drawTo(canvas);
}

function cloneImage(elem){
  var canvas = document.getElementById(elem);
  var times = 2;
  var output = new SimpleImage(image.getWidth()*times,image.getHeight()*times);
  var xmax = image.getWidth() - 1;
  var ymax = image.getHeight() - 1;
  var xshift = 0;
  var yshift = 0;
  //console.log(times,xmax,ymax,xshift,yshift);
  for (var po of output.values()){
    var xo = po.getX();
    var yo = po.getY();
    var x = 0
    var y = 0;
    var xdist = xmax * xshift;
    var ydist = ymax * yshift;
    y = yo - ydist;
    //console.log(xo,yo,x,y,xdist,ydist);
    if (yo%ymax === 0 && yo/ymax >= 1 && xo === 0) {
      yshift = yshift + 1;
    }
    if (xo < xmax){
      xshift = 0;
      xdist = 0;
      x = xo - xdist;
      output.setPixel(xo,yo,image.getPixel(x,y));
    }else if (xo%xmax === 0 && xo/xmax >= 1){
      x = xo - xdist;
      output.setPixel(xo,yo,image.getPixel(x,y));
      xshift = xshift + 1;
      xdist = xmax * xshift;
    }else{
      x = xo - xdist;
      output.setPixel(xo,yo,image.getPixel(x,y));
    }
  }
  output.drawTo(canvas);
}
function blurImage(elem){
  var canvas = document.getElementById(elem);
  var img = new SimpleImage(image.getWidth(),image.getHeight());
  for (var ps of image.values()){
    var x = ps.getX();
    var y = ps.getY();
    if(Math.random() > 0.5){
      var pn = getRandomNearbyPixel(image,x,y);
      img.setPixel(x,y,pn);
    }else{
      img.setPixel(x,y,ps);
    }
  }
  img.drawTo(canvas);
}
function checkBoundaries(coord, limit){
  if(coord < 0 ){return 0}
  if(coord >= limit){return limit - 1}
  return coord;
}
function getRandomNearbyPixel(img, x, y){
    var xn = x + Math.floor(Math.random()*10/4);
    var yn = y + Math.floor(Math.random()*10/4);
    xn = checkBoundaries(xn, img.getWidth());
    yn = checkBoundaries(yn, img.getHeight());
    return img.getPixel(xn,yn);
}
function drawDiagonalColorsSquare(elem){
  var canvas = document.getElementById(elem);
  var width = canvas.width;
  var height = canvas.height;
  var img = new SimpleImage(width,height);
  var ctx = canvas.getContext('2d');
  console.log(width,height);
  for (var pix of img.values()){
    var x = pix.getX();
    var y = pix.getY();
    if(x >= Math.floor(width/height)){
      pix.setRed(255);
      pix.setGreen(0);
      pix.setBlue(0); 
    }else{
      pix.setRed(0);
      pix.setGreen(0);
      pix.setBlue(0); 
    }
  }
  img.drawTo(canvas);
}
function createBelgianFlag(elem){
  var canvas = document.getElementById(elem);
  var width = canvas.width;
  var height = canvas.height;
  var img = new SimpleImage(width,height);
  var ctx = canvas.getContext('2d');
  var bar1 = Math.round(width/3)
  var bar2 = bar1 * 2
  console.log(width,height,bar1,bar2);
  for (var pix of img.values()){
    if(pix.getX() <= bar1){
      pix.setRed(0);
      pix.setGreen(0);
      pix.setBlue(0);
    }else if(pix.getX() > bar1 && pix.getX() <= bar2){
      pix.setRed(250);
      pix.setGreen(250);
      pix.setBlue(0);
    }else{
      pix.setRed(255);
      pix.setGreen(20);
      pix.setBlue(20);
    }
  }
  img.drawTo(canvas);
}
function clearCanvas(canvas) {
  var element = document.getElementById(canvas);
  var ctx = element.getContext('2d');
  ctx.clearRect(0, 0, element.width, element.height);
  element.style.backgroundColor = 'white';
}
function overlayBelgianFlagOnImage(elem){
  var canvas = document.getElementById(elem);
  var img = image;
  var imgW = img.getWidth()
  var bar1 = Math.round(imgW/3)
  var bar2 = bar1 * 2
  for (var pix of img.values()){
    if(pix.getX() <= bar1){
        pix.setRed(pix.getRed() * 0.3);
        pix.setGreen(pix.getGreen() * 0.3);
        pix.setBlue(pix.getBlue() * 0.1);
    }else if(pix.getX() > bar1 && pix.getX() <= bar2){
        pix.setRed(pix.getRed() * 2);
        pix.setGreen(pix.getGreen() * 2);
        pix.setBlue(pix.getBlue() * 0.1);
    }else{
        pix.setRed(pix.getRed() * 15 );
        pix.setGreen(pix.getGreen() * 0.6);
        pix.setBlue(pix.getBlue() * 0.6);
     }
  }
  clearCanvas(elem);
  img.drawTo(canvas); 
}
function uploadAndDisplayImage(elem, input) {
  var canvas = document.getElementById(elem);
  var file = document.getElementById(input); 
  image = new SimpleImage(file);
  image.drawTo(canvas);
}
function imageToGrayScale(elem){
  var img = image;
  var canvas = document.getElementById(elem);
  for (var pix of img.values()){
    var avgcol = (pix.getRed() + pix.getBlue() + pix.getGreen()) / 3;
    pix.setRed(avgcol);
    pix.setBlue(avgcol);
    pix.setGreen(avgcol);
  }
  clearCanvas(elem);
  img.drawTo(canvas);
}
</script>
</head>
<body>

<ul>
												<li><button class="button" style="font-size:65px;">Pixel 2.0 -- <g:link controller="product" action="model6"><span style="color:#D23B2A">WEB3 NFT Mind Model 6 >></span></g:link></span></button></li>
												<br><br>
												 
												
											
											</ul>

<h1 id='mainHeaderH1' class='mainHeaderH1'>Pixel 2.0 Image Manipulation Algorithms - Javascript Library / API Toolkit</h1>

<p>Mofying an image</p>
<canvas id='canvasUploadImage' class='canvasRoundCorders'></canvas>
<input id='loadImage' type='file' accept='image/*' onchange="uploadAndDisplayImage('canvasUploadImage','loadImage')">

<input id='grayscaleImage' type='button' value='grayscale' onclick="imageToGrayScale('canvas1')"> <input id='belgianImage' type='button' value='belgian' onclick="overlayBelgianFlagOnImage('canvas1')">

<hr>

<p>Belgian Flag</p>
<canvas id='canvasBelgianFlag' class='canvasRoundCorders'></canvas>
<input id='buttonCreateBelgianFlag' type='button' value='Belgian flag' onclick="createBelgianFlag('canvasBelgianFlag')">

<p>Diagonal on Rectangle</p>
<canvas id='canvasDiagonalColorSquare' class='canvasRoundCorders'></canvas>
<input id='buttonDiagColSquare' type='button' value='Create Square' onclick="drawDiagonalColorsSquare('canvasDiagonalColorSquare')">

<hr>

<p>Blurring an image</p>
<canvas id='canvasImageToBlur' class='canvasRoundCorders'></canvas>
<input id='loadImageToBlur' type='file' accept='image/*' onchange="uploadAndDisplayImage('canvasImageToBlur','loadImageToBlur')">

<input id='blurImage' type='button' value='Blur now'  onclick="blurImage('canvasUploadImageToBlur')"> <input id='cloneImage' type='button' value='Clone now' onclick="cloneImage('canvasImageToBlur')">

<hr>

<p>Generate image by algorithm</p>
<canvas id='canvasImageByAlgorithm' class='canvasRoundCorders'></canvas>
<input id='imageByAlgorithm' type='button' value='Generate' onclick="drawImageByAlgorithm('canvasImageByAlgorithm')">
</body>
</html>
    