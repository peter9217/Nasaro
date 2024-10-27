// 게시글 수정 시 삭제된 이미지의 순서를 기록할 Set 객체 생성
var preview = document.getElementsByClassName("preview");
var deleteSet = new Set(); // 순서x, 중복x 
var deleteImage = Array.from(document.getElementsByClassName("deleteImg"));
// -> x버튼 클릭 시 순서를 한 번만 저장하는 용도
var inputImage = Array.from(document.getElementsByClassName("introImg"));
inputImage.forEach(e=>{
    e.addEventListener('change', event=>{
        console.log(e)
        console.log(e)
        const file = event.target.files[0];
        if(file != undefined){ // 파일이 선택되었을 때
        const reader = new FileReader(); // 파일을 읽는 객체

        reader.readAsDataURL(file);
        // 지정된 파일을 읽은 후 result 변수에 URL 형식으로 저장

        reader.onload = i => { // 파일을 다 읽은 후 수행
            e.parentNode.childNodes[1].setAttribute("src", i.target.result);

        }


        } else{ // 선택 후 취소 되었을 때 -> 선택된 파일 없음 -> 미리보기 삭제
            e.parentNode.childNodes[1].removeAttribute("src");
        }
    })
})
deleteImage.forEach(e=>{
    e.addEventListener('click',  event => {
        console.log("asd")
        console.log(e.parentNode.childNodes[1].childNodes[3])
        e.parentNode.childNodes[1].childNodes[1].removeAttribute("src");

        // input type = "file" 태그의 value를 삭제
        // ** input type="file" 의 value는 ""(빈칸)만 대입 가능
        e.parentNode.childNodes[1].childNodes[3].value = "";
        e.parentNode.childNodes[1].childNodes[3].src="/images/common/no-image.png";
        // deleteSet에 삭제된 이미지 순서 추가
    })
})
//     console.log(i)
//   inputImage[i].addEventListener('change', e=>{
//     console.log(preview);
//     console.log(i);
//   // 파일이 선택되거나, 선택 후 취소 되었을 때
//       const file = e.target.files[0]; // 선택된 파일의 데이터

//       if(file != undefined){ // 파일이 선택되었을 때
//           const reader = new FileReader(); // 파일을 읽는 객체

//           reader.readAsDataURL(file);
//           // 지정된 파일을 읽은 후 result 변수에 URL 형식으로 저장

//           reader.onload = e => { // 파일을 다 읽은 후 수행
//               preview[i].setAttribute("src", e.target.result);

//               // 이미지가 성공적으로 읽어지면 
//               // delelteSet에서 삭제
//               deleteSet.delete(i);
//           }


//       } else{ // 선택 후 취소 되었을 때 -> 선택된 파일 없음 -> 미리보기 삭제
//           preview[i].removeAttribute("src");
//       }
//   })
//   
// }
if (document.getElementById("plus")!=null) {
    document.getElementById("plus").addEventListener('click', () => {
        const adminMemberTable = document.getElementById("adminMemberTable");
        const imgset = document.querySelector("#imgset");
        adminMemberTable.append(imgset.cloneNode(true));
        preview = document.getElementsByClassName("preview");
        deleteImage = document.getElementsByClassName("deleteImg");
        inputImage = document.getElementsByClassName("introImg");
    })
    
}


const adminMemberArray = Array.from(document.getElementsByClassName("adminMember-button"));
adminMemberArray.forEach(e=>{
    e.addEventListener('click', event=>{
        const memberNo=e.parentElement.parentElement.children[0].textContent;
        const memberRank =e.parentElement.parentElement.children[3].children[0].value;
        const memberDelFl=e.parentElement.parentElement.children[5].children[0].value;
        const memberObject = {
            memberNo: memberNo,
            memberRank: memberRank,
            memberDelFl: memberDelFl
        };
        if(memberRank=='M'){
            const result = window.confirm("매니저로 임명 시 게시판 삭제가 가능합니다.")
            if (!result){
                window.alert("취소되었습니다.");
                location.reload();
                return;
            }

        }
        console.log(memberObject)
        fetch("/admin/modifyMember", {
            method: "POST",
            headers: {
                "Content-Type": "application/json",
                [csrfHeader]: csrfToken
            },
            body: JSON.stringify(memberObject), // JSON 형식으로 데이터 변환
        })
        .then(response => {
            if (!response.ok) {
                throw new Error('Network response was not ok');
            }
            return response.text(); // 응답 데이터를 JSON으로 파싱하여 반환
        })
        .then(data => {
            // 서버에서 온 데이터를 처리합니다.
            window.alert(data);
        })
        .catch(error => {
            // 오류 처리
            console.error('There was a problem with your fetch operation:', error);
        });
    })
})
const topArray = Array.from(document.getElementsByClassName("slide-text-top"));
if (topArray!=null) {
    topArray.forEach(e=>{
        e.addEventListener('change', event=> {
            console.log(e.value);
            e.parentElement.parentElement.parentElement.children[0].children[0].children[1].style.top = e.value+"%";
        })
    })
}
const leftArray = Array.from(document.getElementsByClassName("slide-text-left"));
if (leftArray!=null) {
    leftArray.forEach(e=>{
        e.addEventListener('change', event=> {
            e.parentElement.parentElement.parentElement.children[0].children[0].children[1].style.left = e.value+"%";
        })
    })
}
const textArray = Array.from(document.getElementsByClassName("slide-text-area"));
if (textArray!=null) {
    textArray.forEach(e=>{
        e.addEventListener('input', event=> {
            e.parentElement.parentElement.children[0].children[0].children[1].value=e.value
            
        })
    })
}
const colorArray = Array.from(document.getElementsByClassName("slide-text-color"));
if (colorArray!=null) {
    colorArray.forEach(e=>{
        e.addEventListener('input', event=> {
            e.parentElement.parentElement.parentElement.children[0].children[0].children[1].style.color=e.value;
            
        })
    })
}