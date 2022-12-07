# backendTechnicalTest
Project for the technical interview with MCA

### Installation

_Below is a list of how you can install and setting the app.

1. Install docker
2. Clone the repo
   ```sh
   git clone https://github.com/ariasangel/backendTechnicalTest.git
   ```
3. Docker instructions
   ```sh
   sudo docker build -t springio/similar-products .
   sudo docker run -p 5000:5000 springio/similar-products
   ```

<!-- USAGE EXAMPLES -->
## Testing and Self-evaluation (From the original project)
2. Clone the repo
   ```sh
   git clone https://github.com/dalogax/backendDevTest.git
   ```
You can run the same test we will put through your application. You just need to have docker installed.

First of all, you may need to enable file sharing for the `shared` folder on your docker dashboard -> settings -> resources -> file sharing.

Then you can start the mocks and other needed infrastructure with the following command.
```
docker-compose up -d simulado influxdb grafana
```
Check that mocks are working with a sample request to [http://localhost:3001/product/1/similarids](http://localhost:3001/product/1/similarids).
