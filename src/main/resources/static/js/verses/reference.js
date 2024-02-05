const reference = document.getElementsByClassName('reference-content');
for(let r of reference) {
    /* 공지 게시글 클릭시 */
    r.addEventListener('click', e => {
      const reference = e.target.parentElement.getAttribute("value");
      document.location.href="/reference/referenceDetail/"+reference
    });
  
};