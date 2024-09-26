function generateTOC(yamlData) {
    const tocElement = document.getElementById('toc');
    const toc = document.createElement('ul');
    toc.innerHTML = '<h2>目录</h2>';
  
    // 创建一个以标签为键的对象
    const tagGroups = {};
  
    for (const [path, pathItem] of Object.entries(yamlData.paths)) {
      for (const [method, operation] of Object.entries(pathItem)) {
        if (typeof operation === 'object' && operation !== null) {
          const tag = operation.tags && operation.tags[0];
          const operationId = operation.operationId;
  
          if (tag && operationId) {
            if (!tagGroups[tag]) {
              tagGroups[tag] = [];
            }
            tagGroups[tag].push({ path, method, operation, operationId });
          }
        }
      }
    }
  
    // 为每个标签创建一个组
    for (const [tag, operations] of Object.entries(tagGroups)) {
      const tagElement = document.createElement('li');
      const tagSpan = document.createElement('span');
      tagSpan.textContent = tag;
      tagSpan.className = 'toc-path';
      tagElement.appendChild(tagSpan);
  
      const operationList = document.createElement('ul');
      operationList.style.display = 'none'; // 初始状态为隐藏
  
      for (const { method, operation, operationId } of operations) {
        const operationElement = document.createElement('li');
        const operationText = document.createElement('span');
        operationText.textContent = `${method.toUpperCase()}: ${operation.summary || ''}`;
        operationText.className = 'operation-item';
        operationElement.appendChild(operationText);
        operationList.appendChild(operationElement);
      }
  
      if (operationList.children.length > 0) {
        tagElement.appendChild(operationList);
        
        // 添加点击事件来切换操作列表的可见性
        tagSpan.addEventListener('click', () => {
          operationList.style.display = operationList.style.display === 'none' ? 'block' : 'none';
          tagSpan.classList.toggle('expanded');
        });
      }
  
      toc.appendChild(tagElement);
    }
  
    tocElement.appendChild(toc);
  }
  function fetchYamlAndGenerateTOC() {
    fetch('api-docs.yaml')
      .then(response => response.text())
      .then(yamlText => {
        const yamlData = jsyaml.load(yamlText);
        generateTOC(yamlData);
      })
      .catch(error => {
        console.error('Error fetching or parsing YAML:', error);
      });
  }
  
  function setupTOCToggle() {
    const tocToggle = document.getElementById('toc-toggle');
    const body = document.body;
  
    tocToggle.addEventListener('click', () => {
      body.classList.toggle('toc-hidden');
      tocToggle.textContent = body.classList.contains('toc-hidden') ? '目录' : '收起';
    });
  }
  
  // 当页面加载完成时执行
  document.addEventListener('DOMContentLoaded', () => {
    fetchYamlAndGenerateTOC();
    setupTOCToggle();
  });