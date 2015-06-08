var today = new Date();

var birth_year = 1989;/// 바이오 리듬을 알아볼 사람의 생일 중의 "년" <br>
var birth_month = 5;/// 바이오 리듬을 알아볼 사람의 생일 중의 "월" <br>
var birth_day = 16;/// 바이오 리듬을 알아볼 사람의 생일 중의 "일" <br>
var today_year = today.getFullYear();/// 오늘의 "년" <br>
var today_month = today.getMonth();/// 오늘의 "월" <br>
var today_day = today.getDate();/// 오늘의 "일" <br>
var month_day = new Array();

month_day[1] = 31;
month_day[2] = 59;
month_day[3] = 90;
month_day[4] = 120;
month_day[5] = 151;
month_day[6] = 181;
month_day[7] = 212;
month_day[8] = 243;
month_day[9] = 273;
month_day[10] = 304;
month_day[11] = 334;
month_day[12] = 365;

year = (today_year - 1) * 365;// 년수 계산 <br>
check_month = today_month - 1;
month = month_day[check_month];//월수 개산 <br>

if (month > 2)
{ 
if ((month % 4) == 0 && (month % 100) !=0 || (month % 400) == 0)
{ 
month = month +1;
}
} 

total = year + month + today_day;

my_year = (birth_year - 1) * 365;// 년수 계산 <br>
my_check_month = birth_month - 1;
my_month = month_day[my_check_month];//월수 개산 <br>
my_total = my_year + my_month + birth_day;
totalday = total - my_total;
pis = 3.141592654;

physical = Math.ceil(Math.sin((totalday / 23) * 2 * pis) * 100);
emotional = Math.ceil(Math.sin((totalday / 28) * 2 * pis) * 100);
mental = Math.ceil(Math.sin((totalday / 33) * 2 * pis) * 100);
intuitive = Math.ceil(Math.sin((totalday / 38) * 2 * pis) * 100);


document.querySelector("#physical").innerHTML=physical;
document.querySelector("#emotional").innerHTML=emotional;
document.querySelector("#mental").innerHTML=mental;
document.querySelector("#intuitive").innerHTML=intuitive;
