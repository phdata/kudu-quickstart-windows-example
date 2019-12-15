param ($kudu_dir)
$env:KUDU_QUICKSTART_IP=(Get-NetIPConfiguration | Where-Object {$_.IPv4DefaultGateway -ne $null -and $_.NetAdapter.Status -ne "Disconnected"}).IPv4Address.IPAddress
docker-compose -f $kudu_dir/docker/quickstart.yml up