import org.springframework.cloud.contract.spec.Contract

Contract.make {
    description "should return Guest when ID input is given"
    request{
        method GET()
        url("/guests/bff7c415bdab4563a7ec2c4e09e0b28e") {

        }
    }
    response {
        body($(consumer(file("response.json"))))
        status 200
    }
}