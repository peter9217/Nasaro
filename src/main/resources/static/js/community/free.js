const free = document.getElementsByClassName('free-content');
for(let n of free) {
    /* 공지 게시글 클릭시 */
    n.addEventListener('click', e => {
      const no = e.target.parentElement.getAttribute("value");
      document.location.href="/community/freeDetail/"+no
    });
  
};