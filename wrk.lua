counter = 0

charset = {}  do -- [0-9a-zA-Z]
    for c = 48, 57  do table.insert(charset, string.char(c)) end
    for c = 65, 90  do table.insert(charset, string.char(c)) end
    for c = 97, 122 do table.insert(charset, string.char(c)) end
end

function randomString(length)
    if not length or length <= 0 then return '' end
    math.randomseed(os.clock()^5)
    return randomString(length - 1) .. charset[math.random(1, #charset)]
end


function request()
    counter = counter + 1
    local task_id = string.format("%d-%s", os.time()*10000+math.random(1, 10000) + counter, randomString(10))

    local data = [[{
        "width": 50,
        "height": 50,
        "backgroundColor": "#d04c44",
        "format": "png",
        "texts": [
            {
                "text": "task_id: %s"
            }
        ]
    }]]
    wrk.method = "POST"
    wrk.body   = string.format(data, tostring(task_id))
    wrk.headers["Content-Type"] = "application/json"
    -- return wrk.format("POST", "/v1/engine/task")
    return wrk.format()
end