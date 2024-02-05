const sharing = document.getElementsByClassName('sharing-content');
for(let s of sharing) {
    /* 공지 게시글 클릭시 */
    s.addEventListener('click', e => {
      const no = e.target.parentElement.getAttribute("value");
      document.location.href="/community/sharingDetail/"+no
    });
  
};