const verses = document.getElementsByClassName('verses-content');
for(let v of verses) {
    /* 말쓰ㅡㅁ 게시글 클릭시 */
    v.addEventListener('click', e => {
      const versesNo = e.target.parentElement.getAttribute("value");
      if (versesNo!=null) {
        document.location.href="/verses/versesDetail/"+versesNo
      }
    });
  
};

//  말쓰ㅡㅁ 수정하기 버튼
if(document.getElementById("versesModify") != null) {
  document.getElementById("versesModify").addEventListener("click", e => {
    e.preventDefault(); // 기존의 클릭 동작을 취소함
    
    const token = document.getElementById("csrf").value
    const name = document.getElementById("csrfName").value
    const versesNo = document.getElementById("versesModify").getAttribute("value")

    // 새로운 폼 엘리먼트 생성
    const form = document.createElement('form');
    form.setAttribute('method', 'post');
    form.setAttribute('action', '/verses/modify/' + versesNo);

    // 토큰을 담을 hidden input 생성
    const tokenInput = document.createElement('input');
    tokenInput.setAttribute('type', 'hidden');
    tokenInput.setAttribute('name', name);
    tokenInput.setAttribute('value', token);

    // 폼에 hidden input 추가
    form.appendChild(tokenInput);

    // 폼을 body에 추가하고 submit
    document.body.appendChild(form);
    form.submit();
  });
}




/* 말쓰ㅡㅁ 삭제 버튼 */
if (document.getElementById("versesDelete") != null) {
  document.getElementById("versesDelete").addEventListener("click", e=>{
    const versesNo = e.target.value;
    const token = document.getElementById("csrf").value
    const name = document.getElementById("csrfName").value
    
    if (confirm("삭제하시겠습니까?")) {
        fetch("/verses/delete", {
            method: "POST",
            headers: {
              "Content-Type": "application/json",
              [csrfHeader]: csrfToken
            },
            body: JSON.stringify(versesNo)
        })
        .then(response => response.text())
        .then(() => {
            setTimeout(function() {
                document.location.href = "/verses/verses";
            }, 500);
        })
        .catch(e => {
            console.log(e);
        });
    }
  })
}