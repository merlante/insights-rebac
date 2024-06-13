# Function to check if a command is available
command_exists() {
  command -v "$1" >/dev/null 2>&1
}

# Check if Docker is installed
if command_exists docker; then
  echo "Docker is installed. Using Docker."
  export DOCKER=docker
else
  echo "Docker not found. Checking for Podman."

  # Check if Podman is installed
  if command_exists podman; then
    echo "Podman is installed. Using Podman."
    export DOCKER=podman
  else
    echo "Podman not found. Please install either Docker or Podman to proceed."
    exit 1
  fi

   # Check if docker-compose is installed
    if command_exists docker-compose; then
      echo "docker-compose is installed."
    else

      # Check if podman-compose is installed
      if command_exists podman-compose; then
        echo "podman-compose is installed."
      else
        echo "docker-compose or podman-compose not found. Please install either docker-compose or podman-compose to proceed."
        exit 1
      fi
    fi
fi

