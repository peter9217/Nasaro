const csrfHeader = document.querySelector('meta[name="_csrf_header"]').content;
const csrfToken = document.querySelector('meta[name="_csrf"]').content;
// const menuClick = document.getElementsByClassName("bottom-LeftMenu");
// const menuArray = Array.from(menuClick);
// menuArray.forEach(e => {
//     e.addEventListener('click', event=>{
//         const clickedIndex = menuArray.indexOf(e);
//         console.log("클릭된 요소의 인덱스:", clickedIndex);
//         if (clickedIndex==0) {
//             console.log("0")
//             document.getElementById("bottomLeftMenuNotice").style.display="block";
//             document.getElementById("bottomLeftMenuBoard").style.display="none";
//             document.getElementsByClassName("bottom-LeftMenu")[1].style.borderBottom = "1px solid #ccc"
//             document.getElementsByClassName("bottom-LeftMenu")[0].style.borderBottom = "0px solid #ccc"
//             document.getElementsByClassName("bottom-LeftMenu")[0].style.borderTop = "1px solid #ccc"
//             document.getElementsByClassName("bottom-LeftMenu")[1].style.borderTop = "0px solid #ccc"
//         }
//         if (clickedIndex==1) {
//             console.log("1")
//             document.getElementById("bottomLeftMenuBoard").style.display="block";
//             document.getElementById("bottomLeftMenuNotice").style.display="none";
//             document.getElementsByClassName("bottom-LeftMenu")[0].style.borderBottom = "1px solid #ccc"
//             document.getElementsByClassName("bottom-LeftMenu")[1].style.borderBottom = "0px solid #ccc"
//             document.getElementsByClassName("bottom-LeftMenu")[1].style.borderTop = "1px solid #ccc"
//             document.getElementsByClassName("bottom-LeftMenu")[0].style.borderTop = "0px solid #ccc"
//         }
//     })
// });


function autoResize(textarea) {
    textarea.style.height = 'auto'; // 기본 높이로 초기화
    textarea.style.height = textarea.scrollHeight + 'px'; // 스크롤 높이로 설정
}
if(document.getElementById('myTextarea')!=null){
    document.getElementById('myTextarea').addEventListener('keydown', function(event) {
        if (KeyboardEvent.keyCode === 13) { // 13은 엔터 키의 keyCode입니다.
            event.preventDefault(); // 기본 동작(폼 제출)을 막습니다.
        }
    });
}
document.getElementById("login").addEventListener("click", e => {
    const topLogin = document.getElementById("topLogin");
    let rightValue = parseInt(topLogin.style.right);
    
    if (isNaN(rightValue)) {
        rightValue = -290;
        
    }
    
    if (rightValue === -290) {
        topLogin.style.right = "-70px";
    } else {
        topLogin.style.right = "-290px";
    }
});
