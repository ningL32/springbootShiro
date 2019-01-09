/*cookie存储所有玩过游戏的用户名*/
var userName=null;
/*先验证用户名和密码是否合法,再验证用户名是否存在*/
function checkData(){
	var userName = document.getElementById("userNo").value;
	var myRex = /[0-9]{10}/;
	if (!myRex.test(userName)) {
		alert('输入的考生号有误');
		return false;
	}
	var val2 = document.getElementById("userCode1").value;
	if (val2 != '123456') {
		alert('是不是密码忘记了');
		return false;
	}
/*	if(setUser.has(val)){//用户已存在,提示并且不进行存储
		alert("该考生号已被占用,请重新输入");
		return false;
	}*/
    setCookie(userName,1);//只存储当天登陆的用户名
	alert('登陆成功');
	return true;
}
/*设置cookie值的函数*/
function setCookie(cname,exdays)
{
  var d = new Date();
  d.setTime(d.getTime()+(exdays*24*60*60*1000));
  var expires = "expires="+d.toGMTString();
  document.cookie = cname + ";" + expires;
}
/*访问cookie的值*/
function getCookie(cname)
{
  var name = cname + "=";
  var ca = document.cookie.split(';');
  for(var i=0; i<ca.length; i++) 
  {
    var c = ca[i].trim();
    if (c.indexOf(name)==0) 
    return    alert(c.substring(name.length,c.length)); 
  }
  return "";
}
/*=================================================================================================================*/
/*系统预设17条成语*/
var arrs = new Array('及格才柺','江狼拔喵','望梅止渴','杞人忧天','网功豪牢','将入职腊','一马当先','指厚为马','郑或买履','鹰击长空','卧新喝胆','壮无凌云','飞发横祸','祸注萧亿','墙时众键推','推图被','按享索骥','横边立码','问到型功','功酒名陈','见龙卸甲','破釜沉舟','相濡以沫');
/*随机得到的数句,确保数组不重复*/
var setRd =new Set();
var idom = new Array();
/*得到的分数存放处*/
var scors = new Array();
/*全局条数*/
var tempCont=0;
/*测试函数*/
function aaa(){
	var arrsLength = arrs.length;
	alert(arrsLength);
	setRd.add('a');
	setRd.add('b');
	setRd.add('a');
	alert(setRd.add('a'));
	alert(setRd.size);
	setRd.add('c');
	for (var x of setRd) {
		alert(x);
	}	
}
/*加载完界面即获取单词*/
function getWords(){
	var nums = new Array();
	var cont =0;
	var flag =true;
	do{
		var idex = Math.floor(Math.random()*arrs.length);
		if(setRd.has(arrs[idex]) == false){//set中没有这个值
			setRd.add(arrs[idex]);
		}
		if(setRd.size == 10){
			flag=false;
		}
	}while(flag);
	for ( var ele of setRd) {
		idom[cont] = ele;
		cont++;
	}
	idom[cont]="点击系一条查看分数";
	document.getElementById("next").disabled =true;
}
/*开始评测--循环任务10次*/
function startTest(){
	    //点击之后,才能点击下一条	
	    document.getElementById("startup").disabled =true;
		if(timer==null){
		document.getElementById("next").disabled =false;
		timer = window.setInterval("Timing()",500);  //
	}
}

/*倒计时50 秒*/
var timing = 50;
/*定时器*/
var timer =null;
/*用户输入的成语存储*/
var inarrs = new Array();
/*倒计时函数,绑定在下一条事件*/
function Timing(){
	document.getElementById('idom').innerHTML = idom[tempCont];//飞来的成语
	if(timing<=0){
	 /*显示倒计时间*/
	document.getElementById("tCont").innerHTML=timing;
	/*显示当前条数*/
	document.getElementById("conts").innerHTML=tempCont+1; 
	inarrs[tempCont] = document.getElementById("inData").value; //用户输入的数据进行存储,不管对不对都是零分
	document.getElementById("inData").value=''; //刷新输入框
	/*30秒时间到-->自动进入下一条成语*/
	timing=50;//重新赋初值
	/*清楚倒计时*/
	window.clearInterval(timer);
	timer=null;
	tempCont++; //自动进入下一条,并将全局计数器加1
	startTest();
	}
	if(tempCont==10){
		document.getElementById("tCont").innerHTML='50';
	    document.getElementById("conts").innerHTML='所有';
	    document.getElementById('idom').innerHTML = idom[tempCont];//飞来的成语
	    document.getElementById("inData").value=''; //刷新输入框	  
	    window.clearInterval(timer);
	    timer=null;
	    /*获得当前分数*/
	    var sumScore = getScore();
		document.getElementById('inData').value = "恁的分数为:"+sumScore.toFixed(1);
		document.getElementById("next").disabled =true;
		return;
	}
	document.getElementById("conts").innerHTML=tempCont+1;
	timing--; //倒计时	
	document.getElementById("tCont").innerHTML=timing;
	
}
//点击下一条事件
function toNext(){
	//在50秒内完成输入,存储用户输入的数据,先不进行校验
	if(tempCont>=10){
		document.getElementById("tCont").innerHTML='50';
	    document.getElementById("conts").innerHTML='所有';
	    document.getElementById('idom').innerHTML = idom[tempCont];//飞来的成语
	    document.getElementById("inData").value=''; //刷新输入框	  
	    window.clearInterval(timer);
	    timer=null;
	    /*获得当前分数*/
	    var sumScore = getScore();
		document.getElementById('inData').value = "恁的分数为:"+sumScore.toFixed(1);
		document.getElementById("next").disabled =true;
		return;
	}else{
	scors[tempCont] =timing*0.2;
	inarrs[tempCont] =  document.getElementById('inData').value;
	document.getElementById('idom').innerHTML = idom[tempCont];//飞来的成语
	document.getElementById("inData").value=''; //刷新输入框
	if(tempCont == 9){
		document.getElementById('next').value ="查看成绩";
	}
	tempCont++;  //自动进入下一条,并将全局计数器加1
	timing=50; /*重新赋初值*/
	window.clearInterval(timer);
	timer=null;
	startTest();
	}	
}
/*总分数为total*/
var total =0;
function getScore(){	
	for(var i=0;i<10;i++){
		if(!(idom[i]==inarrs[i])){
			alert("第"+(i+1)+'条错误');
			scors[i]=0.0;
		}
	}
	for (var ele of scors) {
		total =total+ele;
	}
	return total;
}
function lookRand(){
	/*location.href ="HTML/查看排名.html";*/
	window.open('查看排名.html');

    window.history.back(-1);返回上一页
	getCookie(userName);	
}
