if (document.getElementById("send")!=null) {
  document.getElementById("send").addEventListener("click", e=>{
    const memberTel = document.getElementById("signUpTelF").value
    const token = document.getElementById("csrf").value
    const name = document.getElementById("csrfName").value
    fetch('/signUp/sendSms',{
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
  fetch('/signUp/smsConfirm',{
    method: 'POST',
    headers: {
      "Content-Type": "application/json",
      [csrfHeader]: csrfToken
    },
    body: JSON.stringify({memberTel,smsCode})
  })
  .then(response => response.text()) 
  .then(string => {
    console.log(string)
    if(string == "코드가 일치합니다."){
      checkObj.memberTel = true; 
      document.getElementById("signUpTelF").readOnly=true
      document.getElementById("smsCode").readOnly=true
      document.getElementById("phoneMessage").innerText="인증완료"
      document.getElementById("send").style.display="none"
      document.getElementById("smsCodeSet").style.display="none"

    }else{
      idMessage.innerText = string
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







// //전화번호
// // 전화번호 유효성 검사
// const memberTel = document.getElementById("signUpTelF");
// const phoneMessage = document.getElementById("phoneMessage");

// // 전화번호가 입력되었을 때
// memberTel.addEventListener("input", () => {

//    // 전화번호가 입력되지 않은 경우
//    if(memberTel.value.trim().length == 0) {
//     memberTel.value = ""; // 띄어쓰기 못 넣게 하기
//     phoneMessage.innerText="'-'를 제외한 숫자만 입력 바랍니다."
//     memberTel.classList.remove("confirm", "error"); // 검정 글씨로 바꾼다
//      // 빈칸 == 유효하지 않다
//     return;
// }

//   // 정규표현식으로 유효성 검사
//   const regEx = /^0(1[01679]|2|[3-6][1-5]|70)[1-9]\d{2,3}\d{4}$/;
//   if(regEx.test(memberTel.value)) { // 유효
//       phoneMessage.innerText="유효한 전화번호 입니다."
//       memberTel.classList.add("confirm");
//       memberTel.classList.remove("error");
      
//     } else { // 무효
//       phoneMessage.innerText="유효하지 않은 전화번호 입니다."
//       memberTel.classList.add("error");
//       memberTel.classList.remove("confirm");
      
//   }
// });



document.getElementById("signOutConfirm").addEventListener("click", e=>{
  console.log("asd")
  if(confirm("정말 회원탈퇴 하시겠습니까?")){
    for(let key in checkObj){
      if(!checkObj[key]){ // 각 key에 대한 value(true/false)를 얻어와
        // false인 경우 == 유효하지 않다!
        switch(key){
          case "memberTel" : 
          alert("전화번호가 유효하지 않습니다"); break;
        }
        e.preventDefault(); // form 태그 기본 이벤트 제거
        return; // 함수 종료
      }
    }
  }
});
