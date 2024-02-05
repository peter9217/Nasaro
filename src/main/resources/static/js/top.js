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

document.getElementById("login").addEventListener("click", e => {
    const topLogin = document.getElementById("topLogin");
    let rightValue = parseInt(topLogin.style.right);
    
    if (isNaN(rightValue)) {
        rightValue = -220;
        
    }
    
    if (rightValue === -220) {
        topLogin.style.right = "0";
    } else {
        topLogin.style.right = "-220px";
    }
});
