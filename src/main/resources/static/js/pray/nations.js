const nations = document.getElementsByClassName('nations-content');
for(let n of nations) {
    /* 공지 게시글 클릭시 */
    n.addEventListener('click', e => {
      const no = e.target.parentElement.getAttribute("value");
      document.location.href="/pray/nationsDetail/"+no
    });
  
};