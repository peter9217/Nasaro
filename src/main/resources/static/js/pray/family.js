const family = document.getElementsByClassName('family-content');
for(let f of family) {
    /* 공지 게시글 클릭시 */
    f.addEventListener('click', e => {
      const no = e.target.parentElement.getAttribute("value");
      document.location.href="/pray/familyDetail/"+no
    });
  
};