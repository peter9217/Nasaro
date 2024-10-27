console.log("asd")

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
    "memberId" : false,
    "memberName" : false,
    "memberNickname" : false,
    "memberPw" : false,
    "memberPwConfirm" : false,
    "memberTel" : false
    // "mypageSignUptermsAll" : false
};

// 아이디 유효성 검사
const memberId = document.getElementById("memberId");
const idMessage = document.getElementById("idMessage");

// 정규 표현식을 이용해서 유효한 형식인지 판별
if (memberId!=null) {
  
  memberId.addEventListener("input", () => {
      console.log(memberId.value)
      // 아이디가 입력되지 않은 경우
      if(memberId.value.trim().length == 0) {
        memberId.value = ""; // 띄어쓰기 못 넣게 하기
        idMessage.innerText="(영문소문자/숫자, 4~16자)"
        idMessage.classList.remove("confirm", "error"); // 검정 글씨로 바꾼다
        memberId.classList.remove("confirm", "error"); // 검정 글씨로 바꾼다
        checkObj.memberId = false; // 빈칸 == 유효하지 않다
        return;
    }
  
    const regEx = /^[a-z\d]{4,16}$/;
    if(regEx.test(memberId.value) ){ // 유효한 경우
      
      fetch('/sign/checkId?memberId=' + memberId.value)
      .then(response => response.text()) 
      .then(count => {
        if(count == 0){
          idMessage.innerText = "(영문소문자/숫자, 4~16자)"
          memberId.classList.remove("error"); 
          memberId.classList.remove("confirm");  
          checkObj.memberId = true; 
      
        }else{
          idMessage.innerText = "중복된 아이디 입니다."
          memberId.classList.add("error");  
          memberId.classList.remove("confirm");  
          checkObj.memberId = false; 
        }
      }) 
      .catch(err => console.log(err)); // 예외 처리
    } else { 
      idMessage.innerText = "유효하지 않은 형식입니다."
      memberId.classList.add("error");  
      memberId.classList.remove("confirm");  
      checkObj.memberId = false; 
    }
  })
}


// 이름 정규식 확인
const memberName = document.getElementById("memberName");
memberName.addEventListener("input", () => {

    // 이름이 입력되지 않은 경우
    if(memberName.value.trim().length == 0) {
      memberName.value = ""; // 띄어쓰기 못 넣게 하기
      checkObj.memberName = false; // 빈칸 == 유효하지 않다
      return;
  }

// 정규식 최소 8글자 최대 16글자 문자/숫자/특수문자 중 두가지 이상 조합 비밀번호
  const regEx = /^[a-zA-Z가-힣]{1,20}$/;

  // 정규식을 통과한다면
  if(regEx.test(memberName.value)){
    checkObj.memberName = true;

  // 통과하지 않는다면
  }else{
    checkObj.memberName = false;
  }
});


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



// 아이디 유효성 검사
const memberNickname = document.getElementById("memberNickname");
const nicknameMessage = document.getElementById("nicknameMessage");

// 정규 표현식을 이용해서 유효한 형식인지 판별
if (memberNickname!=null) {
  
  memberNickname.addEventListener("input", () => {
      console.log(memberNickname.value)
      // 아이디가 입력되지 않은 경우
      if(memberNickname.value.trim().length == 0) {
          memberNickname.value = ""; // 띄어쓰기 못 넣게 하기
          nicknameMessage.innerText="(영문소문자/숫자, 2~10자)"
          nicknameMessage.classList.remove("confirm", "error"); // 검정 글씨로 바꾼다
        memberNickname.classList.remove("confirm", "error"); // 검정 글씨로 바꾼다
        checkObj.memberNickname = false; // 빈칸 == 유효하지 않다
        return;
    }
  
    const regEx = /^[a-zA-Z가-힣\d]{2,10}$/;
    if(regEx.test(memberNickname.value) ){ // 유효한 경우
      
      fetch("/signUp/checkNickname", {
          method: "POST",
          headers: {
            "Content-Type": "application/json",
            [csrfHeader]: csrfToken
          },
          body: JSON.stringify(memberNickname.value), // JSON 형식으로 데이터 변환
      })
      .then(response => response.text()) 
      .then(data => {
        if(data == "true"){
          nicknameMessage.innerText = "(한글/영문소문자/숫자, 4~10자)"
          memberNickname.classList.remove("error"); 
          memberNickname.classList.remove("confirm");  
          checkObj.memberNickname = true; 
      
        }else{
          nicknameMessage.innerText = "중복된 아이디 입니다."
          memberNickname.classList.add("error");  
          memberNickname.classList.remove("confirm");  
          checkObj.memberNickname = false; 
        }
      }) 
      .catch(err => console.log(err)); // 예외 처리
    } else { 
      memberNickname.innerText = "유효하지 않은 형식입니다."
      memberNickname.classList.add("error");  
      memberNickname.classList.remove("confirm");  
      checkObj.memberNickname = false; 
    }
  })
}


////////////////////비밀번호
// 비밀번호/ 비밀번호 확인 유효성 검사
const memberPw = document.getElementById("signUpPw");
const memberPwConfirm = document.getElementById("signUpPwConfirm");
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
if (memberPwConfirm!=null) {
  
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
}



  



// 주소

// 주소검색 버튼을 눌렀고
// 우편번호, 기본 주소, 나머지 주소값이 다 입력이 되어 있다면
// checkObj.memberAddress가 true







//전화번호
// 전화번호 유효성 검사
const memberTel = document.getElementById("signUpTelF");
const phoneMessage = document.getElementById("phoneMessage");

// 전화번호가 입력되었을 때
memberTel.addEventListener("input", () => {

   // 전화번호가 입력되지 않은 경우
   if(memberTel.value.trim().length == 0) {
    memberTel.value = ""; // 띄어쓰기 못 넣게 하기
    phoneMessage.innerText="'-'를 제외한 숫자만 입력 바랍니다."
    memberTel.classList.remove("confirm", "error"); // 검정 글씨로 바꾼다
     // 빈칸 == 유효하지 않다
    return;
}

  // 정규표현식으로 유효성 검사
  const regEx = /^0(1[01679]|2|[3-6][1-5]|70)[1-9]\d{2,3}\d{4}$/;
  if(regEx.test(memberTel.value)) { // 유효
      phoneMessage.innerText="유효한 전화번호 입니다."
      memberTel.classList.add("confirm");
      memberTel.classList.remove("error");
      
    } else { // 무효
      phoneMessage.innerText="유효하지 않은 전화번호 입니다."
      memberTel.classList.add("error");
      memberTel.classList.remove("confirm");
      
  }
});




// // 이메일 유효성 검사
// const memberEmail = document.getElementById("signUpMail");

// // 이메일 확인 방법
// memberEmail.addEventListener("input", () => {

//    // 이메일이 입력되지 않은 경우
//    if(memberEmail.value.trim().length == 0) {
//     memberEmail.value = ""; // 띄어쓰기 못 넣게 하기
//     memberEmail.classList.remove("confirm", "error"); // 검정 글씨로 바꾼다
//     emailMessage.innerText=""
//     emailMessage.classList.remove("confirm", "error"); // 검정 글씨로 바꾼다
//     checkObj.memberEmail = false; // 빈칸 == 유효하지 않다
//     return;
// }

//   const regEx = /^[A-Za-z\d\-\_]{4,}@[가-힣\w\-\_]+(\.\w+){1,3}$/;

//   if(regEx.test(memberEmail.value) ){ // 유효한 경우
//     // 중복 검사
//     fetch('/dupCheck/email?memberEmail=' + memberEmail.value)
//     .then(response => response.text()) 
//     .then(count => {
//       if(count == 0){
//         memberEmail.classList.add("confirm");  
//         memberEmail.classList.remove("error");  
//         emailMessage.innerText=""
//         checkObj.memberEmail = true; 
        
//       }else{
//         memberEmail.classList.add("error"); 
//         memberEmail.classList.remove("confirm"); 
//         emailMessage.classList.add("error"); 
//         emailMessage.innerText="중복된 이메일 입니다."
//         checkObj.memberEmail = false; 
//       }
//     })
    
//     .catch(err => console.log(err)); 
//   } else { // 유효하지 않은 경우(무효인 경우)
//     memberEmail.classList.add("error");  
//     memberEmail.classList.remove("confirm");  
//     emailMessage.classList.add("error"); 
//     emailMessage.innerText="유효하지 않은 이메일 입니다."
//     checkObj.memberEmail = false; 
//   }
// })




// 이메일 인증
// const sendAuthKeyBtn = document.getElementById("sendAuthKeyBtn");
// const authKeyMessage = document.getElementById("authKeyMessage");
// let authTimer;
// let authMin = 4;
// let authSec = 59;

// 인증번호를 발송한 이메일 저장
// let tempEmail;

// sendAuthKeyBtn.addEventListener("click", function(){
//     authMin = 4;
//     authSec = 59;

//     checkObj.authKey = false;

//     if(checkObj.memberEmail){ // 중복이 아닌 이메일인 경우
//         /* fetch() API 방식 ajax */
//         fetch("/sendEmail/signUp?memberEmail="+memberEmail.value)
//         .then(resp => resp.text())
//         .then(result => {
//             if(result > 0){
//                 console.log("인증 번호가 발송되었습니다.")
//                 tempEmail = memberEmail.value;
//             }else{
//                 console.log("인증번호 발송 실패")
//             }
//         })
//         .catch(err => {
//             console.log("이메일 발송 중 에러 발생");
//             console.log(err);
//         });
        

//         alert("인증번호가 발송 되었습니다.");

        
//         authKeyMessage.innerText = "05:00";
//         authKeyMessage.classList.remove("confirm");

//         authTimer = window.setInterval(()=>{

//             authKeyMessage.innerText = "0" + authMin + ":" + (authSec<10 ? "0" + authSec : authSec);
            
//             // 남은 시간이 0분 0초인 경우
//             if(authMin == 0 && authSec == 0){
//                 checkObj.authKey = false;
//                 clearInterval(authTimer);
//                 return;
//             }

//             // 0초인 경우
//             if(authSec == 0){
//                 authSec = 60;
//                 authMin--;
//             }


//             authSec--; // 1초 감소

//         }, 1000)

//     } else{
//         alert("중복되지 않은 이메일을 작성해주세요.");
//         memberEmail.focus();
//     }
// });

// // 인증 확인
// const authKey = document.getElementById("authKey");
// const checkAuthKeyBtn = document.getElementById("checkAuthKeyBtn");

// checkAuthKeyBtn.addEventListener("click", function(){

//     if(authMin > 0 || authSec > 0){ // 시간 제한이 지나지 않은 경우에만 인증번호 검사 진행
//         /* fetch API */
//         const obj = {"inputKey":authKey.value, "email":tempEmail}
//         const query = new URLSearchParams(obj).toString()
//         // inputKey=123456&email=user01

//         fetch("/sendEmail/checkAuthKey?" + query)
//         .then(resp => resp.text())
//         .then(result => {
//             if(result > 0){
//                 clearInterval(authTimer);
//                 authKeyMessage.innerText = "인증되었습니다.";
//                 authKeyMessage.classList.add("confirm");
//                 checkObj.authKey = true;

//             } else{
//                 alert("인증번호가 일치하지 않습니다.")
//                 checkObj.authKey = false;
//             }
//         })
//         .catch(err => console.log(err));


//     } else{
//         alert("인증 시간이 만료되었습니다. 다시 시도해주세요.")
//     }

// });










//약관 전체 동의 시 전체 적용 or 전체 해제
// document.getElementById("signUptermsAll").addEventListener("click", e=>{
//   const allButton = document.getElementById("mypageSignUptermsAll")
//   const useButton = document.getElementById("mypageSignUptermsUse")
//   const infoButton = document.getElementById("mypageSignUptermsInfo")
//     if (allButton.checked) {
//         useButton.checked = true;
//         infoButton.checked = true;
//         checkObj.mypageSignUptermsAll = true;
//         return;
//     }
//     if (!allButton.checked) {
//         useButton.checked = false;
//         infoButton.checked = false;
//         checkObj.mypageSignUptermsAll = false;
//         return;
//     }
// });






if (document.getElementById("signUpConfirm")!=null) {
  
  // 회원 가입 form태그가 제출 되었을 때
  document.getElementById("signUpConfirm").addEventListener("click", e=>{
  
    for(let key in checkObj){
      if(!checkObj[key]){ // 각 key에 대한 value(true/false)를 얻어와
        // false인 경우 == 유효하지 않다!
        switch(key){
          case "memberId": 
          alert("아이디가 유효하지 않습니다"); break;
          case "memberPw": 
          alert("비밀번호가 유효하지 않습니다"); break;
          case "memberPwConfirm":
          alert("비밀번호가 동일하지 않았습니다"); break;
          case "memberName":
          alert("이름이 입력되지 않았습니다"); break;
          case "memberTel" : 
          alert("전화번호가 유효하지 않습니다"); break;
          case "memberEmail" : 
          alert("이메일이 유효하지 않습니다"); break;
          case "mypageSignUptermsAll" : 
          alert("필수 약관에 동의하지 않았습니다"); break;
        }
        e.preventDefault(); // form 태그 기본 이벤트 제거
        return; // 함수 종료
      }
    }
  });
}
