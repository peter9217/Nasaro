const family = document.getElementsByClassName('family-content');
for(let f of family) {
    /* 공지 게시글 클릭시 */
    f.addEventListener('click', e => {
      const no = e.target.parentElement.getAttribute("value");
      if (no!=null) {
        document.location.href="/pray/familyDetail/"+no
      }
    });
  
};

//  공지 수정하기 버튼
//  기도노트 수정하기 버튼
if(document.getElementById("familyModify") != null) {
  document.getElementById("familyModify").addEventListener("click", e => {
    e.preventDefault(); // 기존의 클릭 동작을 취소함
    const token = document.getElementById("csrf").value
    const name = document.getElementById("csrfName").value
    const familyNo = document.getElementById("familyModify").getAttribute("value");

    // 새로운 폼 엘리먼트 생성
    const form = document.createElement('form');
    form.setAttribute('method', 'post');
    form.setAttribute('action', '/family/modify/' + familyNo);

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




/* 기도노트 삭제 버튼 */
if (document.getElementById("familyDelete") != null) {
  document.getElementById("familyDelete").addEventListener("click", e=>{
    const familyNo = e.target.value;
    const token = document.getElementById("csrf").value
    const name = document.getElementById("csrfName").value
    
    if (confirm("삭제하시겠습니까?")) {
        fetch("/family/delete", {
            method: "POST",
            headers: {
              "Content-Type": "application/json",
              [csrfHeader]: csrfToken
            },
            body: JSON.stringify(familyNo)
        })
        .then(response => response.text())
        .then(() => {
            setTimeout(function() {
                document.location.href = "/pray/family";
            }, 500);
        })
        .catch(e => {
            console.log(e);
        });
    }
  })
}