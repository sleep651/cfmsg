var tx = new Array (
		 "【新消息】",
		 "【　　　】");

var i=1;
var old_title = window.document.title;
var runflag = false;
var stopflag = false;

//定义i、wo、ud三者的初值
function animatetitle()
{
	i++;
	if(i>100)
	{
		i=0;
	}
	window.document.title=tx[i%2]+old_title;
	parent.window.document.title=tx[i%2]+old_title;
	
	if(!stopflag)
	{
		setTimeout("animatetitle()",500); //数字“100”指每一字符闪烁速度为100毫秒，其改大(小)就闪的慢(快)，可自行设置数值
	}
	else
	{
		window.document.title=old_title;
		parent.window.document.title=old_title;
		runflag = false;
	}
}

function startNotice()
{
	if(!runflag)
	{
		stopflag = false;
		runflag = true;
		animatetitle();
	}
}

function stopNotice()
{
	stopflag = true;
}
//以上几行是对标题栏(title)的function为animatetitle的控制过程
//animatetitle();
//标题栏(title)中运行animatetitle

