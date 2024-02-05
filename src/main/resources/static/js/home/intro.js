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

const notice = document.getElementsByClassName('notice-content');
for(let n of notice) {
    /* 공지 게시글 클릭시 */
    n.addEventListener('click', e => {
      const no = e.target.parentElement.getAttribute("value");
      document.location.href="/community/noticeDetail/"+no
    });
  
};

const family = document.getElementsByClassName('family-content');
for(let f of family) {
    /* 공지 게시글 클릭시 */
    f.addEventListener('click', e => {
      const no = e.target.parentElement.getAttribute("value");
      document.location.href="/pray/familyDetail/"+no
    });
  
};