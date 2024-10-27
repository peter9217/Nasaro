
if (document.getElementById("send")!=null) {
  document.getElementById("send").addEventListener("click", e=>{
    const memberTel = document.getElementById("signUpTelF").value
    
    fetch("/signUp/sendSms",{
      method: 'POST',
      headers: {
        "Content-Type": "application/json",
        [csrfHeader]: csrfToken
      },
      body: memberTel
    })
    .then(response => response.text()) 
    .then(count => {
      
        document.getElementById("smsCodeSet").style.display="flex"
    })
    .catch(err => console.log(err)); // 예외 처리
  })

}
document.getElementById("smsConfirm").addEventListener("click", ()=>{
  const memberTel = document.getElementById("signUpTelF").value
  const smsCode = document.getElementById("smsCode").value
  console.log(memberTel)
  console.log(smsCode)
  fetch('/signUp/smsConfirm',{
    method: 'POST',
    headers: {
      "Content-Type": "application/json",
      [csrfHeader]: csrfToken
    },
    body: JSON.stringify({memberTel, smsCode})
  })
  .then(response => response.text()) 
  .then(string => {
    console.log(string)
    if(string == "코드가 일치합니다."){
      checkObj.memberTel = true; 
      document.getElementById("signUpTelF").readOnly=true
      document.getElementById("smsCode").readOnly=true
      document.getElementById("phoneMessage").innerText="인증완료"
      document.getElementById("smsCodeSet").style.display="none"
      document.getElementById("send").style.display="none"

    }else{
      checkObj.memberTel = false; 
    }
  }) 
  .catch(err => console.log(err)); // 예외 처리
})


// 회원 가입 check확인용 
const checkObj = {
    "memberTel" : false
    // "mypageSignUptermsAll" : false
};







if (document.getElementById("findConfirm")!=null) {
  
  // 회원 가입 form태그가 제출 되었을 때
  document.getElementById("findConfirm").addEventListener("click", e=>{
  
    for(let key in checkObj){
      if(!checkObj[key]){ // 각 key에 대한 value(true/false)를 얻어와
        // false인 경우 == 유효하지 않다!
        switch(key){
          case "memberTel" : 
          alert("전화번호 인증을 진행해주세요"); break;
        }
        e.preventDefault(); // form 태그 기본 이벤트 제거
        return; // 함수 종료
      }
    }
  });
}
