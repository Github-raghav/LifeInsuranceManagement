 console.log("this is new js file");

 const toggleSideBar=()=>{
   
    if($(".sidebar").is(":visible")){

        $(".sidebar").css("display","none");
        $(".content").css("margin-left","0%")
    }else{
        $(".sidebar").css("display","block");
        $(".content").css("margin-left","20%")
    }

 };

 const search=()=>{

    let query=$("#search-input").val()
    console.log(query);

    if(query==''){
        $(".search-result").hide();

    }else{
     console.log(query);
    //send request to server
    let url=`http://localhost:8282/search/${query}`

    fetch(url).then((response)=>{
        return response.json();
    }).then((data)=>{
      console.log(data);
 
     let text=`<div class='list-group'>`
  
     data.forEach(agent => {
         text+=`<a href='#' class='list-group-item list-group-active'> ${agent.Name}`

     });

      text+=`</div>`;
     $(".search-result").html(text);
     $(".search-result").show();
    })


     $(".search-result").show();
    }
 }

 var today = new Date();
var date = today.getFullYear()+'-'+(today.getMonth()+1)+'-'+today.getDate();
//var time = today.getHours() + ":" + today.getMinutes() + ":" + today.getSeconds();
var dateTime = date+' '+time;


// calculator function

      function computeLoan() {
        var amount = document.getElementById("amount").value;
        var rate = document.getElementById("interest_rate").value;
        var months = document.getElementById("months").value;
        var income = document.getElementById("income").value;
        var interest = (amount * rate * 0.01) / months;
        var payment = (amount / months + interest).toFixed(2);
        payment = payment.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
        var asamount = income - payment * 12;
        asamount = asamount.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
        document.getElementById("asamount").innerHTML =
          "Net Amount Assured = $" + asamount;
        document.getElementById("payment").innerHTML =
          "Amount Payable Per Month = $" + payment;
      }
      function policy() {
        var e = document.getElementById("policy_name").value;
        console.log("policy name: " + e);

        var interest_rate = 0;
        if (e == 1) {
          interest_rate = 8;
        }else if(e==2){
	     interest_rate=10;
}else if(e==3){
	     interest_rate=12;
}else if(e==2){
	     interest_rate=15;
}

        console.log(interest_rate);
        document.getElementById("interest_rate").value = interest_rate;
      }

