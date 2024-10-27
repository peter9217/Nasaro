if (document.getElementById("send")!=null) {

  document.getElementById("send").addEventListener("click", e=>{
    const memberTel = document.getElementById("signUpTelF").value
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
if (document.getElementById("smsConfirm")!=null) {

  document.getElementById("smsConfirm").addEventListener("click", ()=>{
    const tel = document.getElementById("signUpTelF").value
    const no = document.getElementById("memberNo").value
    const smsCode = document.getElementById("smsCode").value
    fetch('/signUp/modifyTel',{
      method: 'POST',
      headers: {
        "Content-Type": "application/json",
        [csrfHeader]: csrfToken
      },
      body: JSON.stringify({
        memberTel: tel,
        memberNo: no,
        smsCode: smsCode
      })
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
  
      }else{
        idMessage.innerText = string
        checkObj.memberTel = false; 
      }
    }) 
    .catch(err => console.log(err)); // 예외 처리
  })
}



if (document.getElementById("modifyNickname")!=null) {
    document.getElementById("modifyNickname").addEventListener('click', e=>{
        const nickname = document.getElementById("nickname").value;
        if (nickname!="") {
            fetch("/signUp/checkNickname", {
                method: "POST",
                headers: {
                  "Content-Type": "application/json",
                  [csrfHeader]: csrfToken
                },
                body: JSON.stringify(nickname), // JSON 형식으로 데이터 변환
            })
            .then(response => {
                if (!response.ok) {
                    throw new Error('Network response was not ok');
                }
                return response.text(); // 응답 데이터를 JSON으로 파싱하여 반환
            })
            .then(data => {
                // 서버에서 온 데이터를 처리합니다.
                if (data) {
                }
            })
            .catch(error => {
                // 오류 처리
                console.error('There was a problem with your fetch operation:', error);
            });
            
        }
    })
}


const checkObj = {
  "memberPw" : false,
  "memberPwConfirm" : false,
};

////////////////////비밀번호
// 비밀번호/ 비밀번호 확인 유효성 검사
const memberPw = document.getElementById("memberPw");
const memberPwConfirm = document.getElementById("memberPwConfirm");
if (memberPw!=null) {
    
    memberPw.addEventListener("input", () => {
    
        // 비밀번호가 입력되지 않은 경우
        if(memberPw.value.trim().length == 0) {
          memberPw.value = ""; // 띄어쓰기 못 넣게 하기
          memberPw.classList.remove("confirm", "error"); // 검정 글씨로 바꾼다
          checkObj.memberPw = false; // 빈칸 == 유효하지 않다
          return;
      }
    
    // 정규식 최소 8글자 최대 16글자 문자/숫자/특수문자 중 두가지 이상 조합 비밀번호
      const regEx = /^(?!((?:[A-Za-z]+)|(?:[~!@#$%^&*()_+=]+)|(?:[0-9]+))$)[A-Za-z\d~!@#$%^&*()_+=]{8,16}$/;
    
      if(regEx.test(memberPw.value)){
        checkObj.memberPw = true;
        memberPw.classList.remove("error");
        memberPw.classList.add("confirm");
    
          // 비밀번호 == 비밀번호 확인 (같을 경우)
        if(memberPw.value == memberPwConfirm.value){
          memberPwConfirm.classList.add("confirm");
          memberPwConfirm.classList.remove("error");
          checkObj.memberPw = true;
          
        } else { //다를 경우
          memberPwConfirm.classList.remove("confirm");
          memberPwConfirm.classList.add("error");
          checkObj.memberPwConfirm = false;
        }
      }else{
        memberPw.classList.remove("confirm");
        memberPw.classList.add("error");
        checkObj.memberPw = false;
      }
    });
}
  




// 비밀번호 확인 유효성 검사
memberPwConfirm.addEventListener('input', ()=>{

  
    // 비밀번호가 입력되지 않은 경우
    if(memberPwConfirm.value.trim().length == 0) {
      memberPwConfirm.value = ""; // 띄어쓰기 못 넣게 하기
      memberPwConfirm.classList.remove("confirm", "error"); // 검정 글씨로 바꾼다
      checkObj.memberPwConfirm = false; // 빈칸 == 유효하지 않다
      return;
  }
    
  if(checkObj.memberPw){ // 비밀번호가 유효하게 작성된 경우에
  
    // 비밀번호 == 비밀번호 확인 (같을 경우)
    if(memberPw.value == memberPwConfirm.value){
      memberPwConfirm.classList.add("confirm");
      memberPwConfirm.classList.remove("error");
      checkObj.memberPwConfirm = true;
    } else { //다를 경우
      memberPwConfirm.classList.remove("confirm");
      memberPwConfirm.classList.add("error");
      checkObj.memberPwConfirm = false;
    }
  } else{ // 비밀번호가 유효하지 않은 경우
    checkObj.memberPwConfirm = false;
  }
});

document.getElementById("changePassword").addEventListener("click", e=>{
  // 약관 동의 체크
// const terms = document.getElementsByClassName("mypageSignUp-terms-content");
// for(term of terms) {
//   term.addEventListener("click", () => {
//     if (document.getElementById("mypageSignUptermsUse").checked && document.getElementById("mypageSignUptermsInfo").checked) {
//       document.getElementById("mypageSignUptermsAll").checked = true;
//       checkObj.mypageSignUptermsAll = true;
//     } else{
//       document.getElementById("mypageSignUptermsAll").checked = false;
//       checkObj.mypageSignUptermsAll = false;
//     }
//   });
// };
  alert("asd")
  for(let key in checkObj){
    if(!checkObj[key]){ // 각 key에 대한 value(true/false)를 얻어와
      // false인 경우 == 유효하지 않다!
      switch(key){
        case "memberPw": 
        alert("신규 비밀번호가 유효하지 않습니다"); break;
        case "memberPwConfirm":
        alert("신규 비밀번호가 동일하지 않았습니다"); break;
      }
      e.preventDefault(); // form 태그 기본 이벤트 제거
      return; // 함수 종료
    }
  }
});
