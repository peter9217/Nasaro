// const slideBar = document.getElementById("home-slide");
// const firstImg = document.querySelector("#home-slide img");
// slideBar.append(firstImg.cloneNode(true))
// const slide = Array.from(document.getElementsByClassName("slide-contents"));
// var currentSlide = 1;

// function slideContent() {
//     const slideWidth = (document.getElementsByClassName("slide-contents")[1].width);
    
//     var currentWidth = currentSlide*slideWidth;
//     console.log(currentWidth)
//     console.log(currentSlide);
//     if(currentSlide<slide.length){
        
//         slideBar.style.right = currentWidth+"px";
        
//         currentSlide += 1;
//     }else{
//         slideBar.style.right = 0;
//         currentSlide=1;
//     }
// }
// // setInterval(slideContent, 2000);
// const mySwiper = new Swiper('.swiper-container', {
// autoplay: {
//     delay: 3000
// }
// });






const menuClick = document.getElementsByClassName("bottom-LeftMenu");
const menuArray = Array.from(document.getElementsByClassName("bottom-LeftMenu"));
menuArray.forEach(e => {
    e.addEventListener('click', event=>{
        const clickedIndex = menuArray.indexOf(e);
        console.log("클릭된 요소의 인덱스:", clickedIndex);
        if (clickedIndex==0) {
            console.log("0")
            document.getElementById("bottomLeftMenuNotice").style.display="block";
            document.getElementById("bottomLeftMenuBoard").style.display="none";
            document.getElementsByClassName("bottom-LeftMenu")[1].style.borderBottom = "1px solid #ccc"
            document.getElementsByClassName("bottom-LeftMenu")[0].style.borderBottom = "0px solid #ccc"
            document.getElementsByClassName("bottom-LeftMenu")[0].style.borderTop = "1px solid #ccc"
            document.getElementsByClassName("bottom-LeftMenu")[1].style.borderTop = "0px solid #ccc"
        }
        if (clickedIndex==1) {
            console.log("1")
            document.getElementById("bottomLeftMenuBoard").style.display="block";
            document.getElementById("bottomLeftMenuNotice").style.display="none";
            document.getElementsByClassName("bottom-LeftMenu")[0].style.borderBottom = "1px solid #ccc"
            document.getElementsByClassName("bottom-LeftMenu")[1].style.borderBottom = "0px solid #ccc"
            document.getElementsByClassName("bottom-LeftMenu")[1].style.borderTop = "1px solid #ccc"
            document.getElementsByClassName("bottom-LeftMenu")[0].style.borderTop = "0px solid #ccc"
        }
    })
});

const notice = document.getElementsByClassName('noticeContent');
for(let n of notice) {
    /* 공지 게시글 클릭시 */
    n.addEventListener('click', e => {
      const no = e.target.parentElement.getAttribute("value");
      document.location.href="/community/noticeDetail/"+no
    });
  
};

const family = document.getElementsByClassName('sharingContent');
for(let f of family) {
    /* 공지 게시글 클릭시 */
    f.addEventListener('click', e => {
      const no = e.target.parentElement.getAttribute("value");
      document.location.href="/community/sharingDetail/"+no
    });
  
};

// document.addEventListener('DOMContentLoaded', function() {
//     const thumbnails = document.querySelectorAll('.model-Img');
    
//     if (thumbnails!=null) {
//         thumbnails.forEach(thumbnail => {
//             thumbnail.addEventListener('mouseenter', function() {
//                 const modalId = thumbnail.getAttribute('data-modal');
//                 const modal = document.getElementById(modalId);
//                 modal.style.display = 'flex';
//                 document.getElementById('modal-set').style.height="200px";  // 추가
                
//                 const closeButton = modal.querySelector('.close-button');
                
//                 closeButton.addEventListener('click', function() {
//                     modal.style.display = 'none';
//                     document.getElementById('modal-set').style.height=0;
//                 });
//             });
            
//             thumbnail.addEventListener('mouseleave', function() {
//                 const modalId = thumbnail.getAttribute('data-modal');
//                 const modal = document.getElementById(modalId);
//                 modal.style.display = 'none';
//                 document.getElementById('modal-set').style.height=0;
//             });
//         });
        
//     }
// });
document.getElementById("asd").addEventListener("click", ()=>{
    
    const csrfHeader = document.querySelector('meta[name="_csrf_header"]').content;
    const csrfToken = document.querySelector('meta[name="_csrf"]').content;

    fetch("/main/csrf", {
        method: 'POST',
        headers: {
            "Content-Type": "application/json",
            [csrfHeader]: csrfToken
        },
    });
});
