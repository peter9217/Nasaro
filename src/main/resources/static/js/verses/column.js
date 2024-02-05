const column = document.getElementsByClassName('column-content');
for(let c of column) {
    /* 공지 게시글 클릭시 */
    c.addEventListener('click', e => {
      const columnNo = e.target.parentElement.getAttribute("value");
      document.location.href="/column/columnDetail/"+columnNo
    });
  
};