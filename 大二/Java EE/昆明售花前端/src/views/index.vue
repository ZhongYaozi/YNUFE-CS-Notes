<template>
  <div class="welcome-container">
    <div class="welcome-content">
      <h1 class="welcome-title">欢迎使用昆明售花系统</h1>
      <p class="welcome-subtitle">邂逅春城繁花，便捷管理售花业务</p >
    </div>
  </div>
</template>

<script>
export default {
  name: 'WelcomePage'
}
</script>

<style scoped>
.welcome-container {
  /* 设置容器为全屏高度 */
  height: 100vh;
  width: 100%;

  /* 使用 Flexbox 居中 */
  display: flex;
  justify-content: center; /* 水平居中 */
  align-items: center;     /* 垂直居中 */

  /* 背景渐变效果，调整为贴合花卉、昆明的色调，比如加入一些暖黄、粉紫调 */
  background: linear-gradient(135deg, #f9f1e7 0%, #b38f9d 100%);

  /* 移除默认边距 */
  margin: 0;
  padding: 0;
}

.welcome-content {
  text-align: center;
  color: white;
  padding: 2rem;
  border-radius: 15px;
  background: rgba(255, 255, 255, 0.1);
  backdrop-filter: blur(10px);
  box-shadow: 0 8px 32px rgba(0, 0, 0, 0.1);
  border: 1px solid rgba(255, 255, 255, 0.2);
}

.welcome-title {
  /* 大字体设置 */
  font-size: 3rem;         /* 48px */
  font-weight: 700;        /* 粗体 */
  margin: 0 0 1rem 0;

  /* 文字效果 */
  text-shadow: 2px 2px 4px rgba(0, 0, 0, 0.3);
  letter-spacing: 2px;

  /* 响应式字体 */
  line-height: 1.2;
}

.welcome-subtitle {
  font-size: 1.2rem;       /* 19.2px */
  font-weight: 300;
  margin: 0;
  opacity: 0.9;
  letter-spacing: 1px;
}

/* 响应式设计 */
@media (max-width: 768px) {
  .welcome-title {
    font-size: 2rem;        /* 移动端适配 */
  }

  .welcome-subtitle {
    font-size: 1rem;
  }

  .welcome-content {
    padding: 1.5rem;
    margin: 1rem;
  }
}

@media (max-width: 480px) {
  .welcome-title {
    font-size: 1.5rem;
    letter-spacing: 1px;
  }

  .welcome-subtitle {
    font-size: 0.9rem;
  }
}

/* 动画效果 */
.welcome-content {
  animation: fadeInUp 0.8s ease-out;
}

@keyframes fadeInUp {
  from {
    opacity: 0;
    transform: translateY(30px);
  }
  to {
    opacity: 1;
    transform: translateY(0);
  }
}
</style>